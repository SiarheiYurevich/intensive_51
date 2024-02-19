package task_2.exceptions;

public class MissingDefaultConstructorException extends RuntimeException {
    public MissingDefaultConstructorException(String message, Throwable cause) {
        super(message, cause);
    }
}
