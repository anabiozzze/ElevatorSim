package anabiozzze.elevator.controller;

import anabiozzze.elevator.activity.MovingElev;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Controller {

    private BufferedReader reader; // обработчик ввода новых этажей
    public Queue<Integer> queue = new LinkedList<>(); // очередь этажей
    public static int currentFloor = 1;

    public Controller() {
    }

    // основной метод для ввода последовательности этажей, команд отмены и подтверждения
    public int input() {
        reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Поочередно введите нужные этажи ('q' - выход, 'y' - запустить лифт):");

        try {
            String command = reader.readLine();

            // проверка ввода на соответствие требованиям - 7 этажей + две возможные команды
            String regex = "[1-7, y, q]?";

            if (command.matches(regex)) {

                // реакция на команды
                if (command.equals("q")) System.exit(0);
                if (command.equals("y")) {
                    MovingElev.move(queue);
                }

                // этажи просто добавляем в очередь
                else {
                    int newFloor = Integer.parseInt(command);
                    queue.add(newFloor);
                    System.out.println(newFloor + " этаж добавлен в очередь");
                    input();
                }

                // строка не прошла проверку ввода
            } else {
                System.out.println("Совпадений не найдено. Попробуйте еще раз.");
            }

            // не строка
        } catch (Exception e) {
            System.out.println("Введено неверное значение. Попробуйте еще раз.");
            input();
        }

        return 0;
    }


}
