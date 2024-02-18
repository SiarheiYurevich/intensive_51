package task_2;

import task_2.exceptions.MissingDefaultConstructorException;
import task_2.exceptions.ResourceNotFoundException;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс TestRunner_IrakliBarikhashvili предназначен для поиска и запуска
 * тестовых методов, аннотированных специальными аннотациями,
 * в указанных пакетах.
 */
public class TestRunner_IrakliBarikhashvili {
    /**
     * Расширение файлов класса.
     */
    private static final String CLASS_FILENAME_EXTENSION = ".class";
    /**
     * Разделитель пакетов в именах классов.
     */
    private static final String PACKAGE_SEPARATOR = ".";
    /**
     * Разделитель путей в файловой системе.
     */
    private static final String PATH_SEPARATOR = "/";
    /**
     * Набор пакетов, которые будут сканироваться на наличие тестовых методов.
     */
    private final Set<String> scannedPackages;
    /**
     * Набор аннотаций, которые будут использоваться для поиска тестовых методов.
     */
    private final Set<Class<? extends Annotation>> annotations;

    /**
     * Создает экземпляр класса без указания пакетов и аннотаций.
     */
    public TestRunner_IrakliBarikhashvili() {
        scannedPackages = new HashSet<>();
        annotations = new HashSet<>();
    }

    /**
     * Создает экземпляр класса с указанием пакетов для сканирования
     * и аннотации для поиска тестовых методов.
     *
     * @param annotation аннотация, которая используется для поиска тестовых методов.
     * @param packages пакеты, которые будут сканироваться на наличие тестовых методов.
     * @throws NullPointerException если аннотация или пакеты равны null.
     */
    public TestRunner_IrakliBarikhashvili(Class<? extends Annotation> annotation,
                                          String... packages) {
        this();
        Objects.requireNonNull(packages, "Packages are null");
        for (String pkg : packages) addScanningPackage(pkg);
        addMethodAnnotation(annotation);
    }

    /**
     * Метод для добавления нового пакета в множество сканируемых пакетов.
     * Если пакет является дочерним для уже добавленного пакета, то он не добавляется.
     * Если пакет является родительским для уже добавленного пакета, то дочерний пакет удаляется.
     *
     * @param newPackage строка с именем пакета, который нужно добавить
     * @throws NullPointerException если имя пакета имеет значение null
     * @throws IllegalArgumentException если пакет имеет некорректное имя
     */
    public void addScanningPackage(String newPackage) {
        Objects.requireNonNull(newPackage, "The package being added cannot be null");

        var isCorrectPackage = newPackage
                .matches("^[A-Za-z_]\\w*(?:\\.[A-Za-z_]\\w*)*$");
        if (!isCorrectPackage) throw new IllegalArgumentException("Incorrect package name");

        var iterator = scannedPackages.iterator();
        while (iterator.hasNext()) {
            var addedPackage = iterator.next();
            if (addedPackage.startsWith(newPackage)) {
                iterator.remove();
            } else if (newPackage.startsWith(addedPackage)) {
                return;
            }
        }

        scannedPackages.add(newPackage);
    }

    /**
     * Добавляет аннотацию к набору аннотаций, которые будут использоваться для поиска тестовых методов.
     * @param annotation класс аннотации, которой должны быть помечены тестовые методы
     * @throws NullPointerException если аннотация равна null
     */
    public void addMethodAnnotation(Class<? extends Annotation> annotation) {
        Objects.requireNonNull(annotation, "Annotation is null");
        annotations.add(annotation);
    }

    /**
     * Выполняет все аннотированные тестовые методы, найденные рекурсивно
     * в заданных пакетах.
     *
     * @throws IllegalArgumentException если в списке пакетов содержится неверный
     * @throws ResourceNotFoundException если тестовый класс не удалось найти и загрузить.
     */
    public void run() {
        List<Class<?>> testClasses = getClassesFromPackages();
        var runResults = testClasses.stream()
                .flatMap(clazz -> Stream.of(clazz.getDeclaredMethods()))
                .filter(this::isAnnotatedMethod)
                .map(this::executeMethod)
                .toList();

        printResultsOfAllTests(runResults);
    }

    /**
     * Печатает сводные результаты выполнения всех тестов.
     *
     * @param runResults Список булевых значений, где true означает
     * успешное выполнение теста, а false - неудачное.
     * @throws NullPointerException если список с результатами выполнения тестов
     * имеет значение null
     */
    private void printResultsOfAllTests(List<Boolean> runResults) {
        Objects.requireNonNull(runResults,
                "The list of test run results must not be null");
        var numberOfTests = runResults.size();
        var numberOfSuccessfulTests = runResults.stream()
                .filter(Boolean::booleanValue)
                .count();
        var numberOfFailedTests = numberOfTests - numberOfSuccessfulTests;

        System.err.printf("""
                        Общее количество тестов    : %d
                        Количество успешных тестов : %d
                        Количество неудачных тестов: %d
                        """,
                numberOfTests,
                numberOfSuccessfulTests,
                numberOfFailedTests
        );
    }

    /**
     * Выполняет указанный метод на новом экземпляре класса, которому он принадлежит.
     * В случае, если метод выбрасывает исключение, информация о нём выводится в консоль.
     *
     * @param method метод, который необходимо выполнить.
     * @return <b><i>true</i></b>, если метод выполнился без ошибок, в противном случае <b><i>false</i></b>.
     * @throws NullPointerException если метод имеет значение null
     */
    private boolean executeMethod(Method method) {
        Objects.requireNonNull(method, "Method cannot be null");
        var classContainingMethod = method.getDeclaringClass();
        var instance = newInstance(classContainingMethod);
        try {
            method.setAccessible(true);
            method.invoke(instance);
        } catch (Exception exception) {
            printInfoAboutFailedTest(exception);
            return false;
        }
        return true;
    }

    /**
     * Метод находит в цепочке исключений {@link AssertionError} и выводит
     * для него stack trace. Если в цепочке исключений отсутствует
     * {@link AssertionError}, выводится stack trace для исключения,
     * переданного в аргументы метода.
     *
     * @param exception исключение, возникшее во время выполнения метода
     */
    @SuppressWarnings("CallToPrintStackTrace")
    private void printInfoAboutFailedTest(Throwable exception) {
        Throwable currCause = exception.getCause();
        while (currCause != null) {
            var cause = exception.getCause();
            if (cause instanceof AssertionError assertionError) {
                assertionError.printStackTrace();
                break;
            }
            currCause = currCause.getCause();
        }
        if (currCause == null) exception.printStackTrace();
        System.err.println();
    }

    /**
     * Метод создает новый экземпляр класса, используя конструктор без аргументов.
     *
     * @param clazz класс, для которого нужно создать новый экземпляр
     * @return новый экземпляр класса.
     * @throws NullPointerException если переданный класс равен null.
     * @throws MissingDefaultConstructorException если класс не имеет конструктора по умолчанию,
     * или если при создании экземпляра возникает другая ошибка.
     */
    private <T> T newInstance(Class<T> clazz) {
        Objects.requireNonNull(clazz, "Class is null");
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            String message = "The class does not have a no-args constructor";
            throw new MissingDefaultConstructorException(message, e);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод проверяет, имеет ли указанный метод какую-либо из заданных аннотаций.
     *
     * @param method метод, который необходимо проверить на наличие аннотации.
     * @return <b><i>true</i></b>, если метод имеет хотя бы одну из аннотаций, указанных
     * в списке annotations, в противном случае - <b><i>false</i></b>.
     * @throws NullPointerException если переданный метод имеет значение null.
     */
    private boolean isAnnotatedMethod(Method method) {
        Objects.requireNonNull(method, "Method cannot be null");

        for (var annotation : annotations) {
            if (method.isAnnotationPresent(annotation)) return true;
        }

        return false;
    }

    /**
     * Метод рекурсивно производит поиск всех классов внутри пакетов из списка scannedPackages.
     *
     * @return Список найденных классов в виде объектов {@link Class}.
     * @throws IllegalArgumentException если имя пакета указано неверно (каталог по пакету не удается найти)
     * @throws ResourceNotFoundException если класс с указанным именем не удалось найти и загрузить.
     */
    private List<Class<?>> getClassesFromPackages() {
        List<Class<?>> allClasses = new LinkedList<>();
        Queue<String> packages = new LinkedList<>(scannedPackages);

        while (!packages.isEmpty()) {
            var packageName = packages.poll();

            var classesFromPackage = findClassesFromThisPackage(packageName);
            allClasses.addAll(classesFromPackage);

            var nestedPackages = findNestedPackages(packageName);
            packages.addAll(nestedPackages);
        }

        return allClasses;
    }

    /**
     * Метод для поиска вложенных пакетов в указанном пакете.
     *
     * @param packageName имя пакета, в котором необходимо найти вложенные пакеты (например, java.util)
     * @return список имён вложенных классов для указанного пакета.
     * @throws IllegalArgumentException если имя пакета указано неверно (каталог по пакету не удается найти)
     */
    private List<String> findNestedPackages(String packageName) {
        var directory = findDirectoryByPackageName(packageName);
        var filesFromPackage = directory.listFiles();

        if (filesFromPackage == null) return Collections.emptyList();

        return Arrays.stream(filesFromPackage)
                .filter(File::isDirectory)
                .map(File::getName)
                .map(dir -> String.format("%s.%s", packageName, dir))
                .toList();
    }

    /**
     * Метод производит поиск классов, находящихся внутри пакета с указанным именем.

     * @param packageName имя пакета, в котором необходимо найти классы (например, java.util.concurrent)
     * @return Список найденных классов в виде объектов {@link Class}.
     * @throws IllegalArgumentException если имя пакета указано неверно (каталог по пакету не удается найти)
     * @throws ResourceNotFoundException если класс с указанным именем не удалось найти и загрузить.
     */
    private List<Class<?>> findClassesFromThisPackage(String packageName) {
        var directory = findDirectoryByPackageName(packageName);

        FilenameFilter javaFileFilter = (dir, name) -> name.endsWith(CLASS_FILENAME_EXTENSION);
        File[] filesFromPackage = directory.listFiles(javaFileFilter);

        if (filesFromPackage == null) return Collections.emptyList();

        return Arrays.stream(filesFromPackage)
                .map(File::getName)
                .map(fileName -> fileName.substring(0, fileName.length() - CLASS_FILENAME_EXTENSION.length()))
                .map(fileNameWithoutExtension -> String.format("%s.%s", packageName, fileNameWithoutExtension))
                .map(this::getClassByName)
                .collect(Collectors.toList());
    }

    /**
     * Метод, который производит поиск каталога, соответствующего имени пакета.
     *
     * @param packageName имя пакета для поиска каталога.
     * @return объект типа {@link File}, соответствующий каталогу указанного пакета
     * @throws IllegalArgumentException если каталог не найден.
     */
    private File findDirectoryByPackageName(String packageName) {
        ClassLoader loader = this.getClass().getClassLoader();
        return Optional.of(packageName)
                .map(name -> name.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR))
                .map(loader::getResource)
                .map(URL::getPath)
                .map(File::new)
                .filter(File::exists)
                .orElseThrow(() -> new IllegalArgumentException("Package does not exist: " + packageName));
    }

    /**
     * Метод, который возвращает объект {@link Class} по имени класса.
     *
     * @param name имя класса в виде строки.
     * @return объект {@link Class}, соответствующий указанному имени класса.
     * @throws NullPointerException если переданное имя класса равно null.
     * @throws ResourceNotFoundException если класс с указанным именем не был найден.
     */
    private Class<?> getClassByName(String name) {
        Objects.requireNonNull(name, "Class name cannot be null");
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException exception) {
            String message = "The class with the specified name was not found: " + name;
            throw new ResourceNotFoundException(message, exception);
        }
    }
}
