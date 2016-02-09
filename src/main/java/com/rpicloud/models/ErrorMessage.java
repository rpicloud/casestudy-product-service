package com.rpicloud.models;

/**
 * Created by kaspernissen on 09/02/2016.
 */
public class ErrorMessage {
    private String message;
    private String stackTrace;
    private String path;
    private int timestamp;
    private int errorCode;

    public ErrorMessage(String message, String stackTrace, String path, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.stackTrace = stackTrace;
        this.timestamp = (int) (System.currentTimeMillis() / 1000L);
        this.path = path;
    }

    public ErrorMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}