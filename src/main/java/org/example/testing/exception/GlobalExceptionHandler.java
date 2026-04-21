package org.example.testing.exception;

import org.example.testing.dto.DashboardException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    public class DashboardException extends RuntimeException {
        public DashboardException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ExceptionHandler(DashboardException.class)
    public ResponseEntity<String> handleDashboardException(DashboardException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
    }
}