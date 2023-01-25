package pl.test.exchangerate.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author prabesh on 25/Jan/2023
 */
public class CustomException extends RuntimeException{
    private final String message;
    private final HttpStatus httpStatus;
    public CustomException(String message, HttpStatus httpStatus){
        this.message=message;
        this.httpStatus=httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
