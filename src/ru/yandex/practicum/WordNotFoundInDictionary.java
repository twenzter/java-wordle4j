package ru.yandex.practicum;

public class WordNotFoundInDictionary extends Exception {

    private String message;

    WordNotFoundInDictionary(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
