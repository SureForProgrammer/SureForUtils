package com.surefor.utils.properties;

/**
 * Duplicated properties
 */
public class ProperitesExistException extends Exception {
    public ProperitesExistException(String message) {
        super(message);
    }

    public ProperitesExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProperitesExistException(Throwable cause) {
        super(cause);
    }
}
