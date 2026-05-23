package ru.yandex.practicum;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {

    private WordleDictionaryLoader(PrintWriter logWriter) {}

    public static WordleDictionary createWordleDictionary(PrintWriter logWriter) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("words_ru.txt",
                StandardCharsets.UTF_8))) {

            List<String> words = new ArrayList<>();
            while (bufferedReader.ready()) {
                String bufferedLine = bufferedReader.readLine().trim();
                if (bufferedLine.isBlank()) {
                    break;
                }
                if (bufferedLine.length() != 5) {
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

    private static String formatingWord(String line) {
        StringBuilder word = new StringBuilder(line);
        int indexForChanging = word.indexOf("ё");
        while (indexForChanging != -1) {
            word.setCharAt(indexForChanging,'е');
            indexForChanging = word.indexOf("ё");
        }

        return word.toString().trim().toLowerCase();
    }
}
