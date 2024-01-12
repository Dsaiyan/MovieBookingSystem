package com.scaler.practicebookmyshow.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String m) {
        super(m) ;
    }
}
