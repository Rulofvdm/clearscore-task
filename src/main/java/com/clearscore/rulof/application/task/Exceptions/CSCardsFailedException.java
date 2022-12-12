package com.clearscore.rulof.application.task.Exceptions;

/**
 * If a request to the CSCards API fails
 */
public class CSCardsFailedException extends Exception{
    public CSCardsFailedException(String error) {
        super(error);
    }
}
