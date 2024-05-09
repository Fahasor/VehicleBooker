package vehbook.vehiclebooker.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.format.DateTimeParseException;

@ControllerAdvice
@Aspect
public final class ExceptionHandler {
    @Data
    @AllArgsConstructor
    public static class ExceptionMessage {
        private int httpStatus;
        private String message;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({HttpClientErrorException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            EntityNotFoundException.class,
            ConstraintViolationException.class,
            JsonProcessingException.class,
            DateTimeParseException.class,
            IllegalArgumentException.class})
    public ResponseEntity<ExceptionMessage> handleBadRequestErrorException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionMessage(
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ExceptionMessage> handleInternalServerErrorException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionMessage(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "do not enter not unique value in unique field. more: " + exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ExceptionMessage> handleInternalServerErrorException(RuntimeException exception) {
        return new ResponseEntity<>(
                new ExceptionMessage(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ExceptionMessage> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        return new ResponseEntity<>(
                new ExceptionMessage(
                    HttpStatus.NOT_FOUND.value(),
                    exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionMessage> handleMethodNotAllowed(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionMessage(
                        HttpStatus.METHOD_NOT_ALLOWED.value(),
                        exception.getMessage()),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionMessage> handleMethodArgumentTypeMismatchException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionMessage(
                        HttpStatus.BAD_REQUEST.value(),
                "Invalid input!"),
                HttpStatus.BAD_REQUEST);
    }
}