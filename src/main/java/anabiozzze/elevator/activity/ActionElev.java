package anabiozzze.elevator.activity;

import anabiozzze.elevator.controller.Controller;

public class ActionElev {

    public Controller controller = new Controller();

    public ActionElev(boolean isClose) {
        this.isClose = isClose;
    }

    public ActionElev() {
    }

    protected boolean isClose = true;

    public void openDoor(){

        String result = (isClose==true) ? "...открываются двери...\n" : "";
        System.out.print(result);
        isClose = false;

    }

    public void closeDoor(){
        String result = (isClose==false) ? "...закрываются двери...\n\n" : "";
        System.out.print(result);
        isClose = true;
    }

    public void stop(int floor){
        System.out.println("Лифт прибыл на " + floor + " этаж.");
        controller.currentFloor = floor;
    }

    public void start(int floor){
        System.out.println("Следующий этаж - " + floor + ".");
    }

}
