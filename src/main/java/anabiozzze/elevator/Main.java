package anabiozzze.elevator;


public class Main {

    public static void main(String[] args) {

        ElevatorMain elevator = new ElevatorMain(4444, 5);
        elevator.queue.add(7);
        elevator.queue.add(4);
        elevator.queue.add(1);

        elevator.move(elevator.queue);

    }
}
