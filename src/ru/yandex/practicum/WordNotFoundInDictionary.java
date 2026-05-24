package ru.yandex.practicum;

public class WordNotFoundInDictionary extends RuntimeException {

    WordNotFoundInDictionary(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}