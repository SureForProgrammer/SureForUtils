package com.surefor.utils.properties;

/**
 * Created by chae on 4/10/2015.
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
