package anabiozzze.elevator.controller;

import anabiozzze.elevator.elevator.statemachine.MovingElev;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public final class Controller {


    private BufferedReader reader; // обработчик ввода новых этажей

    private Controller() { }

    private static Controller controller;

    public static Controller getController() {

        if (controller == null) {
            controller = new Controller();
        }

        return controller;
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
                    MovingElev.getMovingElev().move();
                }

                // этажи просто добавляем в очередь
                else {
                    int newFloor = Integer.parseInt(command);
                    MovingElev.getMovingElev().queue.add(newFloor);
                    System.out.println(newFloor + " этаж добавлен в очередь");
                    input();
                }

                // строка не прошла проверку ввода
            } else {
                System.out.println("Совпадений не найдено. Попробуйте еще раз.");
            }

            // не строка
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Введено неверное значение. Попробуйте еще раз.");
            input();
        }

        // если очередь в модели пустая - снова просим ввод, если нет - продолжаем работу
        return (MovingElev.getMovingElev().isEmpty) ? input() : 0;
    }


}
