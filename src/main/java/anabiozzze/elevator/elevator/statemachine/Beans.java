package anabiozzze.elevator.elevator.statemachine;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine
public class Beans {

    @OnTransition(target = "CLOSED_DOOR")
    public void closeDoor() {
        System.out.println("...закрываются двери...\n\n");
    }

    @OnTransition(source = "CLOSED_DOOR", target = "WAITING")
    public void waiting1() {
        MovingElev.getMovingElev().moveWait(2);
    }

    @OnTransition(source = "WAITING", target = "GOING")
    public void startMoving() {
        System.out.println("Следующий этаж - " + MovingElev.getMovingElev().queue.peek() + ".");

        int moveTime = MovingElev.getMovingElev().moveTime;

        while (moveTime!=0) {
            MovingElev.getMovingElev().moveWait(1);
            moveTime--;
            System.out.println("осталось " + moveTime + " с...");
        }
    }

    @OnTransition(source = "GOING", target = "STAY")
    public void stopMoving() {

        int floor = MovingElev.getMovingElev().queue.poll();
        System.out.println("Лифт прибыл на " + floor + " этаж.");
        MovingElev.getMovingElev().currentFloor = floor;
    }


    @OnTransition(source = "STAY", target = "WAITING")
    public void waiting2() {
        MovingElev.getMovingElev().moveWait(2);
    }

    @OnTransition(source = "WAITING", target = "OPEN_DOOR")
    public void openDoor() {
        System.out.println("...открываются двери...\n");
    }

    @OnTransition(source = "OPEN_DOOR", target = "WAITING_PASSAGERS")
    public void waitPass() {
        System.out.println("Ожидание посадки пассажиров.");
        MovingElev.getMovingElev().moveWait(5);
    }
}



