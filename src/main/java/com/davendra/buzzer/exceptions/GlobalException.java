package com.davendra.buzzer.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception ue, WebRequest request) {

        ErrorDetails errorDetails =new ErrorDetails(ue.getMessage(),request.getDescription(false),LocalDateTime.now());

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
