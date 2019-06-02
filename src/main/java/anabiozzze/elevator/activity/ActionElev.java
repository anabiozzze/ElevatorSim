package anabiozzze.elevator.activity;

import anabiozzze.elevator.controller.Controller;

public class ActionElev {

    private Controller controller = new Controller();
    protected boolean isClose = true; // дверь лифта по умолчанию закрыта

    public ActionElev(boolean isClose) {
        this.isClose = isClose;
    }

    public ActionElev() {
    }

    // открываем двери
    protected void openDoor(){

        String result = (isClose==true) ? "...открываются двери...\n" : "";
        System.out.print(result);
        isClose = false;

    }

    // закрываем двери
    protected void closeDoor(){
        String result = (isClose==false) ? "...закрываются двери...\n\n" : "";
        System.out.print(result);
        isClose = true;
    }

    // конец движения
    protected void stop(int floor){
        System.out.println("Лифт прибыл на " + floor + " этаж.");
        controller.currentFloor = floor;
    }


    // начало движения
    protected void start(int floor){
        System.out.println("Следующий этаж - " + floor + ".");
    }


    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean close) {
        isClose = close;
    }
}
