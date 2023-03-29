package golf.mates.demo;

import golf.mates.demo.exceptions.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import jakarta.persistence.NonUniqueResultException;
import org.hibernate.exception.ConstraintViolationException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public static final Instant TIMESTAMP = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();

    @ExceptionHandler({ConstraintViolationException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(Exception ex) {
        log.debug(ex.getMessage(), ex.getCause());
        return new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(), TIMESTAMP);
    }

    @ExceptionHandler({NonUniqueResultException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflictRequestException(Exception ex) {
        log.debug(ex.getMessage(), ex.getCause());
        return new ErrorResponse(String.valueOf(HttpStatus.CONFLICT.value()), ex.getMessage(), TIMESTAMP);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}