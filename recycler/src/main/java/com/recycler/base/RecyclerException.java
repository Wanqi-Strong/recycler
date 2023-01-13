package com.recycler.base;

public class RecyclerException extends RuntimeException {
    private int errorCode = -1;
    private String errorMessage;

    public RecyclerException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public RecyclerException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public RecyclerException(Exception exception) {
        super(exception);
        this.errorMessage = exception.getMessage();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
