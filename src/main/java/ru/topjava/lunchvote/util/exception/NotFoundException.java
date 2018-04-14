package ru.topjava.lunchvote.util.exception;

/**
 * Created by Антон on 08.04.2018.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
