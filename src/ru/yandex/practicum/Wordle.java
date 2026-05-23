package ru.yandex.practicum;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {

    public static void main(String[] args) {

        try (PrintWriter logWriter = new PrintWriter(new FileWriter("log.txt",
                     StandardCharsets.UTF_8, true))) {
            WordleGame wordleGame = new WordleGame(logWriter);
            wordleGame.startGame();
        } catch (IOException e) {
            System.out.println("Ошибка в работе с внутренними файлами!");
        }
    }

}
