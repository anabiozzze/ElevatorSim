package anabiozzze.elevator.elevator.statemachine;

import anabiozzze.elevator.elevator.Elevator;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.statemachine.StateMachine;

import java.util.LinkedList;
import java.util.Queue;

public final class MovingElev {

    private MovingElev() {}

    private static MovingElev movingElev;

    public static MovingElev getMovingElev() {

        if (movingElev == null) {
            movingElev = new MovingElev();
        }

        return movingElev;
    }

    // создаем контекст для работы с аннотациями spring statemachine
    private ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("anabiozzze/elevator/elevator/statemachine");
    private final StateMachine<States, Events> stateMachine = context.getBean(StateMachine.class);

    private Elevator elevator = new Elevator();

    // очередь этажей
    public Queue<Integer> queue = new LinkedList<>();

    // мьютекс для ожидания лифта в методе moveWait()
    private static final Object lock = new Object();

    // флаг для проверки очереди и повторного запроса ввода, если очередь пуста
    public boolean isEmpty;

    protected int moveTime = 0;
    protected int currentFloor = 1;


    // метод движения вверх и вниз.
    public void move() {

        stateMachine.start();

        // закрыли двери
        stateMachine.sendEvent(Events.CLOSE_DOOR);

        // считаем кол-во следующих этажей, ждем 2 сек
        floorDiff(queue);
        stateMachine.sendEvent(Events.WAIT);

        // пока едем - счетчик выводит секунды на консоль
        stateMachine.sendEvent(Events.MOVE);

        // приехали, удалили этаж из очереди, дали сообщение
        stateMachine.sendEvent(Events.STAY);

        // подождали 2 секунды
        stateMachine.sendEvent(Events.WAIT);

        // открыли двери
        stateMachine.sendEvent(Events.OPEN_DOOR);

        // 5 секунд даем на выход и вход пассажиров
        stateMachine.sendEvent(Events.WAIT_PASSAGERS);

        // закрыли двери
        stateMachine.sendEvent(Events.CLOSE_DOOR);

        // если очередь пуста - спрашиваем пользователя о дальнейших действиях
        if (queue.peek()==null) {
            isEmpty = true;
        }

        // если в очереди остались этажи - запускаем процесс сначала, вернув машину в исх. положение
        if (queue.peek()!=null) {
            isEmpty = false;
            stateMachine.stop();
            move();
        }
    }



    // метод для ожидания нужного кол-ва времени
    public void moveWait(int sec) {

        synchronized(lock) {
            try {
                lock.wait(sec * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // метод для рассчета разницы между этажами и подсчета времени движения
    public int floorDiff(Queue<Integer> queue){
        int result = Math.abs(currentFloor - queue.peek());
        moveTime = result* elevator.getSpeed();
        return result;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public int getMoveTime() {
        return moveTime;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }
}
