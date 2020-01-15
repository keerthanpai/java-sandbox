package concurrent.cricket;

public class Cricket_v2 {

    CricketState state = CricketState.READY_TO_BOWL;

    public synchronized void bowling() throws InterruptedException{
        while(true) {
            if(state == CricketState.READY_TO_BOWL) {
                System.out.println("Bowler Bowled");
                state = CricketState.READY_TO_BAT;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void batting() throws InterruptedException {

        while(true) {
            if(state == CricketState.READY_TO_BAT) {
                System.out.println("Batsman batted");
                state = CricketState.READY_TO_FIELD;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void fielding() throws InterruptedException {

        while(true) {
            if(state == CricketState.READY_TO_FIELD) {
                System.out.println("Filelder Fielded");
                state = CricketState.READY_TO_BOWL;
                notifyAll();
            } else {
                wait();
            }
        }
    }

}
