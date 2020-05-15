package com.cg.feedback.trainer.exceptions;

import java.sql.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomErrorAdvice {
  
    @ExceptionHandler({ CustomException.class, SQLException.class, NullPointerException.class })
    public ResponseEntity<ErrorInfo> handle1(CustomException ce) {
        ErrorInfo error = new ErrorInfo(ce.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}