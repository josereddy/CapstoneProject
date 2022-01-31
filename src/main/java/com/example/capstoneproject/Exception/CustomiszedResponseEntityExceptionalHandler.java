package com.example.capstoneproject.Exception;

import com.example.capstoneproject.DTO.Order_DTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomiszedResponseEntityExceptionalHandler extends ResponseEntityExceptionHandler {


    private static final Logger log = LogManager.getLogger(CustomiszedResponseEntityExceptionalHandler.class.getName());



    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object>  handleUserNotFoundException(UserNotFoundException ue, WebRequest request)
    {
        log.error("Exception :ORDER ITEM NOT FOUND");
        ExceptionResponse  er = new ExceptionResponse(new Date(),ue.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @Override
    protected final ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Exception: MISMATCHED CLIENT CALL REQUEST");
        ExceptionResponse  er = new ExceptionResponse(new Date(),"Please check Are you using the exact Http CAll  ",request.getDescription(false));
        return new ResponseEntity<Object>(er,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Exception: CLIENT EXCEED THE VALIDATION NORMS");
        ExceptionResponse  er = new ExceptionResponse(new Date(),"Size exceeded for the order status  ",request.getDescription(false));
        return new ResponseEntity<Object>(er,HttpStatus.BAD_REQUEST);
    }
}


