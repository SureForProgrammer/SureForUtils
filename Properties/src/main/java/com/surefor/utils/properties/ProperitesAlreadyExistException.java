package com.surefor.utils.properties;

/**
 * Created by chae on 4/10/2015.
 */
public class ProperitesAlreadyExistException extends Exception {
    public ProperitesAlreadyExistException(String message) {
        super(message);
    }

    public ProperitesAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProperitesAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
