package ru.yandex.practicum;

import java.io.*;
import java.util.*;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {

    private final List<String> words;
    private List<String> filteredWords;

    PrintWriter logWriter;

    public WordleDictionary(List<String> words, PrintWriter logWriter) {
        this.words = words;
        this.filteredWords = new ArrayList<>(words);
        this.logWriter = logWriter;

    }

    public List<String> getWords() {
        return words;
    }

    public List<String> getFilteredWords() {
        return filteredWords;
    }


    public String compareGuessWithAnswer(String guess, String answer) {
        StringBuilder result = new StringBuilder();
        int rightLettersCounter = 0;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess.charAt(i)) {
                filterWords(i, answer,true);
                result.append("+");
                rightLettersCounter++;
            } else {
                result.append(guess.charAt(i));
            }
        }

        for (int i = 0; i < answer.length() - 1; i++) {
            for (int o = 0; o < answer.length(); o++) {
                if (answer.charAt(i) == guess.charAt(o) && result.charAt(o) != '+' && result.charAt(i) != '+') {
                    result.replace(i,i+1,"^");
                }
            }
        }

        int lastInvalidIndex = -1;
        for (int i = 0; i < answer.length(); i++) {
            if (result.charAt(i) != '+' && result.charAt(i) != '^') {
                result.replace(i,i+1,"-");
                lastInvalidIndex = i;
            }
        }

        filterWords(lastInvalidIndex,answer,false);

        if (rightLettersCounter != Wordle.WORD_LENGTH) {
            filteredWords.remove(guess);
        }

        return result.toString();
    }

    private void filterWords(int index, String answer, boolean deleteOrAddFlag) {
        List<String> newWords = new ArrayList<>();
        if (deleteOrAddFlag) {
            for (String word: filteredWords) {
                if (word.charAt(index) == answer.charAt(index)) {
                    newWords.add(word);
                }
            }
            filteredWords = newWords;
        } else {
            for (int i = 0; i < filteredWords.size(); i++) {
                if (index != -1 && filteredWords.get(i).charAt(index) != answer.charAt(index)) {
                    filteredWords.remove(i);
                    i--;
                }
            }
        }
    }

}
