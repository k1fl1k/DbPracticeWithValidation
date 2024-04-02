package com.k1fl1k.dbpractice.persistance.service.exception;

public class DatabaseConnectionException extends RuntimeException{
    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
