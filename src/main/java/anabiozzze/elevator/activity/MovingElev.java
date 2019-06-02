package anabiozzze.elevator.activity;

import anabiozzze.elevator.Elevator;
import anabiozzze.elevator.controller.Controller;

import java.util.Queue;

public class MovingElev {

    public static ActionElev action = new ActionElev();
    public static Controller controller = new Controller();

    private static final Object lock = new Object();
    public static int moveTime = 0;

    public MovingElev() {
    }

    public static void start() {

        if (controller.queue.peek() == controller.currentFloor) {
            action.openDoor();
            action.stop(controller.queue.peek());

        } else {
            String result = (controller.queue.peek() < controller.currentFloor) ? "Лифт опускается." : "Лифт поднимается.";
            System.out.println(result);
            move(controller.queue);

        }
    }

    public static void move(Queue<Integer> queue) {

        action.closeDoor();
        action.start(queue.peek());
        floorDiff(queue);
        moveWait(Elevator.getWaitTime());


        while (moveTime!=0) {
            moveWait(1);
            moveTime--;
            System.out.println("осталось " + moveTime + " с...");
        }

        action.stop(queue.poll());

        moveWait(Elevator.getWaitTime());
        action.openDoor();

        System.out.println("Ожидание посадки пассажиров.");
        moveWait(5);
        action.closeDoor();

        if (queue.peek()==null) {
            controller.input();
        }

        if (queue.peek()!=null) {
            move(queue);
        }
    }

    public static void moveWait(int sec) {

        synchronized(lock) {
            try {
                lock.wait(sec * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static int floorDiff(Queue<Integer> queue){
        int result = Math.abs(controller.currentFloor - queue.peek());
        moveTime = result* Elevator.getSpeed();
        return result;
    }

}
