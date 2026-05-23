package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

class WordleTest {

    public static WordleGame wordleGame;
    public static PrintWriter logWriter;

    @BeforeAll
    public static void beforeAll() throws IOException {
        logWriter = new PrintWriter(new FileWriter("log.txt",StandardCharsets.UTF_8, true));
        wordleGame = new WordleGame(logWriter);
        wordleGame.startGame();
    }

    @Test
    public void checkGetRandomWord() {
        String word = wordleGame.getRandomWord(wordleGame.getDictionary().getWords());
        Assertions.assertTrue(wordleGame.getDictionary().getWords().contains(word));
    }

    @Test
    public void checkFormatingWord() {
        String word = "ЁЖиК";
        word = WordleDictionaryLoader.formatingWord(word);
        Assertions.assertEquals("ежик",word);
    }

    @Test
    public void checkCreateWordleDictionary() throws IOException {
        WordleDictionary wordleDictionary = WordleDictionaryLoader.createWordleDictionary(logWriter);
        Assertions.assertEquals(wordleDictionary.getWords(),wordleDictionary.getFilteredWords());
    }

    @Test
    public void checkCompareGuessWithAnswerWithMistakes() throws IOException {
        WordleDictionary wordleDictionary = WordleDictionaryLoader.createWordleDictionary(logWriter);
        String response = wordleDictionary.compareGuessWithAnswer("герой","гонец");
        Assertions.assertEquals("+^-^-", response);
    }

    @Test
    public void checkCompareGuessWithAnswerFullRight() throws IOException {
        WordleDictionary wordleDictionary = WordleDictionaryLoader.createWordleDictionary(logWriter);
        String response = wordleDictionary.compareGuessWithAnswer("герой","герой");
        Assertions.assertEquals("+++++", response);
    }

    @Test
    public void checkCompareGuessWithAnswerAbsolutelyNotRight() throws IOException {
        WordleDictionary wordleDictionary = WordleDictionaryLoader.createWordleDictionary(logWriter);
        String response = wordleDictionary.compareGuessWithAnswer("эллин","герой");
        Assertions.assertEquals("-----", response);
    }

}
