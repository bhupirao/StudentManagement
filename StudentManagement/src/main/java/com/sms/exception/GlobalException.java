package com.sms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> anyExpHandler(Exception ie , WebRequest rq){


        MyErrorDetails err=new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(ie.getMessage());
        err.setDetails(rq.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }


    public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe, WebRequest rq){

        MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),nfe.getMessage(),rq.getDescription(false));
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(nfe.getMessage());
        err.setDetails(rq.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);


    }

    //validation exception handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorDetails> MyInvalidDataExceptionHandler(MethodArgumentNotValidException me) {
        MyErrorDetails err=new MyErrorDetails();


        err.setTimestamp(LocalDateTime.now());
        err.setMessage("Validation error");
        err.setDetails(me.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);


    }

    // Student Exception
    @ExceptionHandler(StudentException.class)
    public ResponseEntity<MyErrorDetails> myUsersExpHandler(StudentException ie , WebRequest rq){

        MyErrorDetails err=new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(ie.getMessage());
        err.setDetails(rq.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);

    }
    // Course Exception
    @ExceptionHandler(CourseException.class)
    public ResponseEntity<MyErrorDetails> myUsersExpHandler(CourseException ie , WebRequest rq){

        MyErrorDetails err=new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(ie.getMessage());
        err.setDetails(rq.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);

    }


}

