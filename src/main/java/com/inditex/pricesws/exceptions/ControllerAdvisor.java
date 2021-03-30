package com.inditex.pricesws.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private static final Logger loggerController = LoggerFactory.getLogger(ControllerAdvisor.class);

    private static final String ERROR = "error";
    private static final String TIMESTAMP = "timestamp";
    private static final String STATUS = "status";
    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNoDataFoundException( NoDataFoundException ex, WebRequest request) {
        if (loggerController.isErrorEnabled()) {
            loggerController.error(String.format("handleNoDataFoundException request: %s error %S", request.toString(), ExceptionUtils.getStackTrace(ex)));
        }

        Map<String, Object> body = createBody (ex.getMessage(), HttpStatus.NOT_FOUND.value(), "No data found", null);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleInternalServerErrorException( InternalServerErrorException ex, WebRequest request) {
        if (loggerController.isDebugEnabled()) {
            loggerController.error(String.format("handleInternalServerErrorException request: %s error %S", request.toString(), ExceptionUtils.getStackTrace(ex)));
        }

        Map<String, Object> body = createBody(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error", null);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalHandlerException( Exception ex, WebRequest request) {
        if (loggerController.isErrorEnabled()) {
            loggerController.error(String.format("handleException request: %s error %S", request.toString(), ExceptionUtils.getStackTrace(ex)));
        }

        Map<String, Object> body = createBody(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error", null);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException ex,
                                                                   HttpHeaders headers, HttpStatus status,
                                                                   WebRequest request) {
        if (loggerController.isErrorEnabled()) {
            loggerController.error(String.format("handleMethodArgumentNotValid request: %s error %S", request.toString(), ex), Level.ERROR);
        }

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        Map<String, Object> body = createBody(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), "Bad request message", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> createBody (String error, int status, String msg, List<String> errors){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(ERROR, error);
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(STATUS, status);
        body.put(MESSAGE, msg);

        if (errors != null) {
            body.put("errors", errors);
        }
        return body;
    }
}
