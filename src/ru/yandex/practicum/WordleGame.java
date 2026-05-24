package ru.yandex.practicum;

import java.io.*;
import java.util.*;

public class WordleGame {

    private final Random random = new Random();

    private final PrintWriter logWriter;

    private WordleDictionary dictionary;
    private String answer;
    private int steps;


    public WordleGame(PrintWriter logWriter)  {
        this.logWriter = logWriter;
    }

    public void startGame() throws IOException  {
        dictionary = WordleDictionaryLoader.createWordleDictionary(logWriter);
        answer = getRandomWord(dictionary.getWords());
        steps = 0;
    }


    public String getRandomWord(List<String> wordsList) {
        int dictionarySize = wordsList.size();
        int randomWordIndex = random.nextInt(dictionarySize);
        return wordsList.get(randomWordIndex);
    }

    public String getAnswer() {
        return answer;
    }

    public int getSteps() {
        return steps;
    }

    public WordleDictionary getDictionary() {
        return dictionary;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}