package com.clearscore.rulof.application.task.Exceptions;

/**
 * If a request to the ScoredCards API fails
 */
public class ScoredCardsFailedException extends Exception{
    public ScoredCardsFailedException(String error) {
        super(error);
    }
}
