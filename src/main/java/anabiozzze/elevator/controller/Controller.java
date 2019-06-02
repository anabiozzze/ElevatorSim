package anabiozzze.elevator.controller;

import anabiozzze.elevator.activity.MovingElev;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Controller {

    public BufferedReader reader;
    public Queue<Integer> queue = new LinkedList<>();
    public static int currentFloor = 1;

    public Controller() {
    }

    public int input() {
        reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Поочередно введите нужные этажи ('q' - выход, 'y' - запустить лифт):");

        try {
            String command = reader.readLine();
            String regex = "[1-7, y, q]?";

            if (command.matches(regex)) {

                if (command.equals("q")) System.exit(0);
                if (command.equals("y")) {
                    MovingElev.move(queue);
                }
                else {
                    int newFloor = Integer.parseInt(command);
                    queue.add(newFloor);
                    System.out.println(newFloor + " этаж добавлен в очередь");
                    input();
                }


            } else {
                System.out.println("Совпадений не найдено. Попробуйте еще раз.");
            }

        } catch (Exception e) {
            System.out.println("Введено неверное значение. Попробуйте еще раз.");
            input();
        }

        return 0;
    }


}
