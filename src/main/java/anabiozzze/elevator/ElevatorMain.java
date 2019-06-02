package anabiozzze.elevator;

import anabiozzze.elevator.activity.MovingElev;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class ElevatorMain implements ElevatorInt {

    private static int id;
    protected static final int speed = 10;
    protected static final int waitTime = 2;

    public static int currentFloor;
    public Queue<Integer> queue;

    public ElevatorMain() {
    }

    public ElevatorMain(int id, int currentFloor) {
        this.id = id;
        this.currentFloor = currentFloor;

        this.queue = new LinkedList<Integer>();
    }


    @Override
    public void move(Queue<Integer> queue) {
        MovingElev.start(queue);
    }


    public static int getSpeed() {
        return speed;
    }

    public static int getWaitTime() {
        return waitTime;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Queue<Integer> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Integer> queue) {
        this.queue = queue;
    }


    @Override
    public String toString() {
        return "ElevatorMain{" + "id=" + id +
                "currentFloor=" + currentFloor +
                "nextFloor=" + queue.peek() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElevatorMain that = (ElevatorMain) o;
        return currentFloor == that.currentFloor &&
                queue.equals(that.queue) && id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentFloor, queue);
    }
}
