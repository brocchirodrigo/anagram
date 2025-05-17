package br.com.imaginer.anagram.infrastructure.config;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.web.server.ResponseStatusException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(BindException.class)
  public ResponseEntity<ErrorPayload> handleBindException(BindException ex, HttpServletRequest req) {
      log.warn("Validation failed on {} : {}", req.getRequestURI(),
          ex.getFieldErrors().stream()
            .collect(Collectors.toMap(
              FieldError::getField,
              fe -> Objects.toString(fe.getDefaultMessage(), "Invalid Field"),
              (msg1, msg2) -> msg1
            ))
      );
      Map<String,String> errors = ex.getFieldErrors().stream()
          .collect(Collectors.toMap(
              FieldError::getField,
              fe -> Objects.toString(fe.getDefaultMessage(), "Invalid Field"),
              (msg1, msg2) -> msg1
          ));
      return buildResponse(HttpStatus.BAD_REQUEST, ex.getClass().getSimpleName(), errors, req);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorPayload> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
      log.warn("Constraint violations on {} : {}", req.getRequestURI(),
          ex.getConstraintViolations().stream()
            .collect(Collectors.toMap(
              cv -> cv.getPropertyPath().toString(),
              ConstraintViolation::getMessage,
              (msg1, msg2) -> msg1
            ))
      );
      Map<String,String> errors = ex.getConstraintViolations().stream()
          .collect(Collectors.toMap(
              cv -> cv.getPropertyPath().toString(),
              ConstraintViolation::getMessage,
              (msg1, msg2) -> msg1
          ));
      return buildResponse(HttpStatus.BAD_REQUEST, ex.getClass().getSimpleName(), errors, req);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorPayload> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex, HttpServletRequest req) {
      log.warn("Method not allowed: {} {}", ex.getMethod(), req.getRequestURI());
      return buildResponse(HttpStatus.METHOD_NOT_ALLOWED, ex.getClass().getSimpleName(), ex.getMessage(), req);
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErrorPayload> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest req) {
      log.warn("ResponseStatusException on {}: {} {}", req.getRequestURI(), ex.getStatusCode(), ex.getReason());
      return buildResponse((HttpStatus) ex.getStatusCode(), ex.getClass().getSimpleName(), ex.getReason(), req);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ErrorPayload> handleNoHandlerFound(NoHandlerFoundException ex, HttpServletRequest req) {
      log.warn("Invalid path accessed: {} {}", ex.getHttpMethod(), req.getRequestURI());
      return buildResponse(HttpStatus.NOT_FOUND, ex.getClass().getSimpleName(), "The path '" + req.getRequestURI() + "' does not exist", req);
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ErrorPayload> handleNoResourceFound(NoResourceFoundException ex, HttpServletRequest req) {
      log.warn("No resource found at {}", req.getRequestURI());
      return buildResponse(HttpStatus.NOT_FOUND, ex.getClass().getSimpleName(), "The path '" + req.getRequestURI() + "' does not exist", req);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorPayload> handleAll(Exception ex, HttpServletRequest req) {
      log.error("Unexpected error on {}: {}", req.getRequestURI(), ex.getMessage(), ex);
      return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getClass().getSimpleName(), ex.getMessage(), req);
  }

  private ResponseEntity<ErrorPayload> buildResponse(HttpStatus status, String error, Object details, HttpServletRequest req) {
    ErrorPayload body = ErrorPayload.builder()
        .timestamp(Instant.now())
        .status(status.value())
        .error(error)
        .message(details)
        .path(req.getRequestURI())
        .build();
    return new ResponseEntity<>(body, status);
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ErrorPayload {
    private Instant timestamp;
    private int status;
    private String error;
    private Object message;
    private String path;
  }
}