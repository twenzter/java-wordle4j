package ru.yandex.practicum;

public class NoSuchWordLength extends RuntimeException {

    NoSuchWordLength(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}