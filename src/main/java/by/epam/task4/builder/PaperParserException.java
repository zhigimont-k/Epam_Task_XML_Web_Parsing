package by.epam.task4.builder;

public class PaperParserException extends Exception {
    public PaperParserException() {
    }

    public PaperParserException(String message) {
        super(message);
    }

    public PaperParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaperParserException(Throwable cause) {
        super(cause);
    }

    public PaperParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
