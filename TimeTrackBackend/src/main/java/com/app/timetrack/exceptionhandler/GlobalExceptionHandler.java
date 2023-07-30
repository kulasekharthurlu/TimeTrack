package com.app.timetrack.exceptionhandler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }

     @ExceptionHandler(NullPointerException.class)
     public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Null pointer exception occurred");
     }
     
     @ExceptionHandler(TimeTrackException.class)
     public ResponseEntity<Map<String, String>> handleTimeTrackException(TimeTrackException exception) {
    	 return new ResponseEntity<>(Map.of("ErrorCode",exception.getErrorCode(),"ErrorMessage",exception.getErrorMessage()), HttpStatus.BAD_REQUEST);
     }
     @ExceptionHandler(UsernameNotFoundException.class)
     public ResponseEntity<Map<String, String>> handleUsernameNotFoundException(UsernameNotFoundException exception) {
    	 return new ResponseEntity<>(Map.of("ErrorCode","TE-401","ErrorMessage",exception.getMessage()), HttpStatus.BAD_REQUEST);
     }

}
