package concurrent.cricket;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Cricket {

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        CricketState state = CricketState.READY_TO_BOWL;

        public void bowling() throws InterruptedException{
            while(true) {

                lock.lock();
                try {
                    if(state == CricketState.READY_TO_BOWL) {
                        System.out.println("Bowler Bowled.");
                        state = CricketState.READY_TO_BAT;
                        condition.signalAll();
                    } else {
                        condition.await();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

        public void batting() throws InterruptedException {

            while(true) {
                lock.lock();
                try {
                    if(state == CricketState.READY_TO_BAT) {
                        System.out.println("Batsman Batted..");
                        state = CricketState.READY_TO_FIELD;
                        condition.signalAll();
                    } else {
                        condition.await();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

        public void fielding() throws InterruptedException {
            while(true) {
                lock.lock();
                try {
                    if(state == CricketState.READY_TO_FIELD) {
                        System.out.println("Fielder Fielded...");
                        state = CricketState.READY_TO_BOWL;
                        condition.signalAll();
                    } else {
                        condition.await();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }


}
