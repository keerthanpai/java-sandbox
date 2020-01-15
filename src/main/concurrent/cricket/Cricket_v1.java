package concurrent.cricket;

public class Cricket_v1 {

    boolean bowled =false,batted =false,fielded =false;

    public synchronized void bowling() {
        System.out.println("Bowler Bowled");
        bowled =true;
        notifyAll();
    }

    public synchronized void batting() throws InterruptedException {

        while(!batted) {
            if(bowled) {
                System.out.println("Batsman batted");
                batted = true;
                notifyAll();
            } else
                wait();
        }
    }

    public synchronized void fielding() throws InterruptedException {

        while(!fielded) {
            if(bowled && batted) {
                System.out.println("Filelder Fielded");
                fielded = true;
            }
            else
                wait();
        }

    }
}
