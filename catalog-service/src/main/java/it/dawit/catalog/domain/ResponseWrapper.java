package it.dawit.catalog.domain;

public class ResponseWrapper<T> {

    private T wrapped;
    private String message;

    public ResponseWrapper(T wrapped, String message) {
        this.wrapped = wrapped;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public T getWrapped() {
        return wrapped;
    }
}
