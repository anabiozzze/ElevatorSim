package anabiozzze.elevator;

import anabiozzze.elevator.activity.MovingElev;
import anabiozzze.elevator.controller.Controller;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Elevator implements ElevatorInt {

    Controller controller;

    private static int id;
    protected static final int speed = 10;
    protected static final int waitTime = 2;


    public Elevator() {
        id++;
        this.controller = new Controller();
        controller.input();
    }

    @Override
    public void move() {
        MovingElev.start();
    }


    public static int getSpeed() {
        return speed;
    }

    public static int getWaitTime() {
        return waitTime;
    }



    @Override
    public String toString() {
        return "Elevator{" + "id=" + id + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elevator that = (Elevator) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
