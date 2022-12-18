package alphabravo.springsecurity.ExceptionHandler;

public class NoSuchPersonException extends RuntimeException {
    public NoSuchPersonException(String message) {
        super(message);
    }
}
