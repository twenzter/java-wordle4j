package ru.yandex.practicum;

import java.io.*;
import java.util.*;

/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {

    private static final int WORD_LENGTH = 5;
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private final PrintWriter logWriter;
    private WordleDictionary dictionary;
    private String answer;
    private int steps;


    public WordleGame(PrintWriter logWriter) throws IOException {
        this.logWriter = logWriter;
    }

    public void startGame() throws IOException {
        dictionary = WordleDictionaryLoader.createWordleDictionary(logWriter);
        steps = 0;

        answer = getRandomWord();

        PrintMenu();
    }

    public void PrintMenu() {

        System.out.println("Начало игры!\n");
        System.out.println("===WORDLY===");

        while (true) {
            String guess = scanner.nextLine();
            if (guess.isEmpty()) {
                String clue = getRandomWord();
                System.out.println(clue);
                guess = clue;
            }
            if (guess.length() != WORD_LENGTH) {
                System.out.println("Длина отгадываемого слова должна быть ровно 5 символов");
                continue;
            }
            if (!dictionary.getWords().contains(guess)) {
                System.out.println("Слова нет в словаре");
                continue;
            }

            System.out.println(dictionary.compareGuessWithAnswer(guess, answer));
            steps++;

            if (steps == 6) {
                System.out.println("Вы проиграли!");
                System.out.println("Правильное слово - " + answer);
                System.out.println("Затрачено попыток - " + steps);
                break;
            }
            if (answer.equals(guess)) {
                System.out.println("Вы выиграли!");
                System.out.println("Затрачено попыток - " + steps);
                break;
            }
        }

    }

    public String getRandomWord() {
        int dictionarySize = dictionary.size();
        int randomWordIndex = random.nextInt(dictionarySize);
        return dictionary.getWords().get(randomWordIndex);
    }
}
