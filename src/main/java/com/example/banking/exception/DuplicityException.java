package com.example.banking.exception;

/**
 * Excepción lanzada cuando se intenta crear una cuenta duplicada.
 */
public class DuplicityException extends RuntimeException {

    public DuplicityException(String message) {
        super(message);
    }
}
