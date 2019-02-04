package ec.app.testar.exceptions;

public class ShouldHaveAtLeastTwoChildrenException extends Exception {

    public ShouldHaveAtLeastTwoChildrenException() {

    }

    public ShouldHaveAtLeastTwoChildrenException(String message) {
        super(message);
    }

    public ShouldHaveAtLeastTwoChildrenException(Throwable cause) {
        super(cause);
    }

    public ShouldHaveAtLeastTwoChildrenException(String message, Throwable cause) {
        super(message, cause);
    }
}
