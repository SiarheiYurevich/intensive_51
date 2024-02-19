package task_2;

public class Main {
    public static void main(String[] args) {
        var runner = new TestRunner_IrakliBarikhashvili();
        runner.addScanningPackage("task_2");
        runner.addMethodAnnotation(IntensiveTest_IrakliBarikhashvili.class);
        runner.run();
    }
}
