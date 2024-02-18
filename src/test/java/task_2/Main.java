package task_2;

import task_2.exceptions.ResourceNotFoundException;

public class Main {
    public static void main(String[] args) throws ResourceNotFoundException {
        var runner = new TestRunner_IrakliBarikhashvili();
        runner.addScanningPackage("task_2");
        runner.addMethodAnnotation(IntensiveTest_IrakliBarikhashvili.class);
        runner.run();
    }
}
