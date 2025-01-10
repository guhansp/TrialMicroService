package com.bytes.Cards.exception;

public class CardAlreadyExistException extends RuntimeException {
    public CardAlreadyExistException(String message) {
        super(message);
    }
}
