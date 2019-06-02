package anabiozzze.elevator;

import anabiozzze.elevator.controller.Controller;

import java.util.Objects;

public class Elevator  {

    Controller controller;

    private static int id;
    protected static final int speed = 10;
    protected static final int waitTime = 2;

    // контроллер запускается автоматически при создании экземпляра лифта
    public Elevator() {
        id++;
        this.controller = new Controller();
        controller.input();
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
