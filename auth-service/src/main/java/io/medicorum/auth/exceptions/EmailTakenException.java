package io.medicorum.auth.exceptions;

public class EmailTakenException extends RuntimeException {

    public EmailTakenException(String message) {

        super(message);

    }
}