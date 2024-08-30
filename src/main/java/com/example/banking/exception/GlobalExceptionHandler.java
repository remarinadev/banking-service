package com.example.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Controlador de consejo para manejar excepciones en toda la aplicación.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepción de duplicidad y devuelve un código de estado 409.
     * @param ex La excepción de duplicidad.
     * @param request La solicitud web.
     * @return Una respuesta con el mensaje de error y el estado de conflicto.
     */
    @ExceptionHandler(DuplicityException.class)
    public ResponseEntity<?> handleDuplicityException(DuplicityException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Maneja las excepciones genéricas y devuelve un código de estado 500.
     * @param ex La excepción genérica.
     * @param request La solicitud web.
     * @return Una respuesta con el mensaje de error y el estado de error interno del servidor.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
