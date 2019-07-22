package com.github.mcanessa.validraptor.exception;

public class ValidraptorException extends RuntimeException {

    public ValidraptorException() {
        super();
    }

    public ValidraptorException(String message) {
        super(message);
    }

    public ValidraptorException(Throwable throwable) {
        super(throwable);
    }

    public ValidraptorException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
