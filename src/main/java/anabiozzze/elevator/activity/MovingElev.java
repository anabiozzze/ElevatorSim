package anabiozzze.elevator.activity;

import anabiozzze.elevator.Elevator;
import anabiozzze.elevator.controller.Controller;

import java.util.Queue;

public class MovingElev {

    // классы, отвечающие за движение лифта, счетчик этажей и поступающие уведомления
    private static ActionElev action = new ActionElev();
    private static Controller controller = new Controller();

    // мьютекс для ожидания лифта в методе moveWait()
    private static final Object lock = new Object();
    private static int moveTime = 0;

    public MovingElev() {
    }

    // метод движения вверх и вниз.
    public static void move(Queue<Integer> queue) {

        // закрыли двери, подождали 2 секунды, дали уведомление, поехали
        action.closeDoor();
        action.start(queue.peek());
        floorDiff(queue);
        moveWait(Elevator.getWaitTime());

        // пока едем - счетчик выводит секунды на консоль
        while (moveTime!=0) {
            moveWait(1);
            moveTime--;
            System.out.println("осталось " + moveTime + " с...");
        }

        // приехали, удалили этаж из очереди
        action.stop(queue.poll());

        // подождали 2 секунды, открыли двери
        moveWait(Elevator.getWaitTime());
        action.openDoor();

        // 5 секунд даем на выход и вход пассажиров, закрываем дверь
        System.out.println("Ожидание посадки пассажиров.");
        moveWait(5);
        action.closeDoor();

        // если очередь пуста - спрашиваем пользователя о дальнейших действиях
        if (queue.peek()==null) {
            controller.input();
        }

        // если в очереди остались этажи - запускаем процесс сначала
        if (queue.peek()!=null) {
            move(queue);
        }
    }

    // метод для ожидания нужного кол-ва времени
    private static void moveWait(int sec) {

        synchronized(lock) {
            try {
                lock.wait(sec * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // метод для рассчета разницы между этажами и подсчета времени движения
    private static int floorDiff(Queue<Integer> queue){
        int result = Math.abs(controller.currentFloor - queue.peek());
        moveTime = result* Elevator.getSpeed();
        return result;
    }

    public static int getMoveTime() {
        return moveTime;
    }

    public static void setMoveTime(int moveTime) {
        MovingElev.moveTime = moveTime;
    }
}
