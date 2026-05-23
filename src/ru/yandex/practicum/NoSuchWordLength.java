package ru.yandex.practicum;

public class NoSuchWordLength extends RuntimeException {

    private final String message;

    NoSuchWordLength(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
