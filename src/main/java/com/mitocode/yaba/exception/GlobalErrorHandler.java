package com.mitocode.yaba.exception;

import com.mitocode.yaba.dto.GenericResponse;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<CustomErrorResponse>> handleAllException(Exception ex, WebRequest req){

        CustomErrorResponse errorResponse= new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(new GenericResponse<>(500, "BACKEND ERROR", Arrays.asList(errorResponse)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ProblemDetail handlModelNotFoundException(ModelNotFoundException ex, WebRequest req){
        ProblemDetail pd= ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Model Not Found Exception");
        pd.setType(URI.create(req.getContextPath()));
        pd.setProperty("code","404");
        pd.setProperty("message","not-found");
        return pd;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
        CustomErrorResponse errorResponse= new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(new GenericResponse<>(400, "BAD REQUEST", Arrays.asList(errorResponse)), HttpStatus.BAD_REQUEST);
    }
}
