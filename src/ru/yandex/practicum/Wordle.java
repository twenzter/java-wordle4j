package ru.yandex.practicum;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Scanner;

public class Wordle {

    private static final Scanner scanner = new Scanner(System.in);

    public static final int WORD_LENGTH = 5;

    public static void main(String[] args) {
        try (PrintWriter logWriter = new PrintWriter(new FileWriter("log.txt",
                StandardCharsets.UTF_8, true))) {
            WordleGame wordleGame = new WordleGame(logWriter);
            wordleGame.startGame();
            printMenu(wordleGame);
        } catch (IOException e) {
            System.out.println("Ошибка в обработке файлов");
        }
    }

    public static void printMenu(WordleGame wordleGame)  {
        System.out.println("Начало игры!\n");

        System.out.println("===WORDLY===");

        while (true) {
            try {
                String guess = scanner.nextLine();
                if (guess.isEmpty()) {
                    String clue = wordleGame.getRandomWord(wordleGame.getDictionary().getFilteredWords());
                    System.out.println(clue);
                    guess = clue;
                }
                guess = WordleDictionaryLoader.formatingWord(guess);
                if (guess.length() != WORD_LENGTH) {
                    throw new NoSuchWordLength("Длина отгадываемого слова должна быть ровно 5 символов");
                }
                if (!wordleGame.getDictionary().getWords().contains(guess)) {
                    throw new WordNotFoundInDictionary("Слова нет в словаре");
                }

                System.out.println(wordleGame.getDictionary().compareGuessWithAnswer(guess, wordleGame.getAnswer()));
                int steps = wordleGame.getSteps();
                wordleGame.setSteps(++steps);

                if (wordleGame.getSteps() == 6) {
                    System.out.println("Вы проиграли!");
                    System.out.println("Правильное слово - " + wordleGame.getAnswer());
                    System.out.println("Затрачено попыток - " + wordleGame.getSteps());
                    break;
                }
                if (wordleGame.getAnswer().equals(guess)) {
                    System.out.println("Вы выиграли!");
                    System.out.println("Затрачено попыток - " + wordleGame.getSteps());
                    break;
                }
            } catch (NoSuchWordLength | WordNotFoundInDictionary e) {
                System.out.println(e.getMessage());
            }
        }

    }

}