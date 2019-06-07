package anabiozzze.elevator.elevator;

import java.util.Objects;

public class Elevator  {

    private int id;
    protected final int speed = 10;
    protected final int waitTime = 2;

    // контроллер запускается автоматически при создании экземпляра лифта
    public Elevator() {
        id++;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWaitTime() {
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
