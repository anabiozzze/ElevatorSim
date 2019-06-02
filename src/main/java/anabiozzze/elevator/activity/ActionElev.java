package anabiozzze.elevator.activity;


public class ActionElev {

    protected boolean isClose = true;

    public void openDoor(){

        String result = (isClose==true) ? "...открываются двери...\n" : "";
        System.out.print(result);
        isClose = false;

    }

    public void closeDoor(){
        String result = (isClose==false) ? "...закрываются двери...\n" : "";
        System.out.print(result);
        isClose = true;
    }

    public void alertStop(int floor){
        System.out.println("Лифт прибыл на " + floor + " этаж.");
    }

    public void alertStart(int floor){
        System.out.println("Следующий этаж - " + floor + ".");
    }

}
