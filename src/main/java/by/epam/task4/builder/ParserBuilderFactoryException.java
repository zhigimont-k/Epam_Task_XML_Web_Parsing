package by.epam.task4.builder;

public class ParserBuilderFactoryException extends Exception {
    public ParserBuilderFactoryException() {
    }

    public ParserBuilderFactoryException(String message) {
        super(message);
    }

    public ParserBuilderFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserBuilderFactoryException(Throwable cause) {
        super(cause);
    }

    public ParserBuilderFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
