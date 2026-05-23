package ru.yandex.practicum;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordleDictionaryLoader {

    private WordleDictionaryLoader() {
    }

    public static WordleDictionary createWordleDictionary(PrintWriter logWriter) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("words_ru.txt",
                StandardCharsets.UTF_8))) {

            List<String> words = new ArrayList<>();
            while (bufferedReader.ready()) {
                String bufferedLine = bufferedReader.readLine().trim();
                if (bufferedLine.isBlank()) {
                    break;
                }
                if (bufferedLine.length() != Wordle.WORD_LENGTH) {
                    continue;
                }
                String result = formatingWord(bufferedLine);
                words.add(result);
            }
            return new WordleDictionary(words, logWriter);
        } catch (IOException e) {
            e.printStackTrace(logWriter);
            throw e;
        }
    }

    public static String formatingWord(String line) {
        return line.trim().toLowerCase().replace('ё','е');
    }
}