package pl.test.exchangerate.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {


        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<Object> handleOwnantException(CustomException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), ex.getHttpStatus(), request);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptionFromMongo(Exception ex, WebRequest request){
        ex.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return handleExceptionInternal(ex,errorResponse,new HttpHeaders(),HttpStatus.CONFLICT,request);
    }
}