package com.marlowbank.marlow_atm.exception;

public class UnAuthorizedUserException extends RuntimeException{
    public UnAuthorizedUserException(String message) {
        super(message);
    }
}
