package com.renault.upcomingcar.infra;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    record ErrorResponse(Instant timestamp, String path, int status, String error, String message) {}

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex, org.springframework.web.context.request.WebRequest req) {
        ErrorResponse body = new ErrorResponse(Instant.now(), req.getDescription(false), HttpStatus.FORBIDDEN.value(), "FORBIDDEN", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, org.springframework.web.context.request.WebRequest req) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                       .map(e -> e.getField() + ": " + e.getDefaultMessage())
                       .reduce((a,b) -> a + "; " + b).orElse("Validation error");
        ErrorResponse body = new ErrorResponse(Instant.now(), req.getDescription(false), HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", msg);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, org.springframework.web.context.request.WebRequest req) {
        ErrorResponse body = new ErrorResponse(Instant.now(), req.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}