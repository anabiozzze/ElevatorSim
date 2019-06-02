package anabiozzze.elevator.activity;

import anabiozzze.elevator.ElevatorMain;

import java.sql.SQLOutput;
import java.util.Queue;

public class MovingElev {

    private static final Object lock = new Object();
    public static ActionElev action = new ActionElev();
    public static int moveTime = 0;


    public static void start(Queue<Integer> queue) {

        if (queue.peek() == ElevatorMain.currentFloor) {
            action.openDoor();
            action.alertStop(queue.peek());

        } else {
            String result = (queue.peek() < ElevatorMain.currentFloor) ? "Лифт опускается." : "Лифт поднимается.";
            System.out.println(result);
            move(queue);

        }
    }

    public static void move(Queue<Integer> queue) {
        action.closeDoor();
        action.alertStart(queue.peek());
        floorDiff(queue);
        moveWait(ElevatorMain.getWaitTime());

        while (moveTime!=0) {
            moveWait(1);
            moveTime--;
            System.out.println("осталось " + moveTime + " с...");
        }

        action.alertStop(queue.poll());
        moveWait(ElevatorMain.getWaitTime());
        action.openDoor();

        System.out.println("Ожидание посадки пассажиров.");
        moveWait(5);
        action.closeDoor();

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
        int result = Math.abs(ElevatorMain.currentFloor - queue.peek());
        moveTime = result*ElevatorMain.getSpeed();
        return result;
    }
}
