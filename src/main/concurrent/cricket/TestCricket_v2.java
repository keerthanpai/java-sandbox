package concurrent.cricket;

public class TestCricket_v2 {

    public static void main(String[] args) {

        Cricket_v2 cricket = new Cricket_v2();

        Runnable batting = () -> {
            try {
                cricket.batting();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
               e.printStackTrace();
            }
        };

        Runnable bowling = () -> {
            try {
                cricket.bowling();
            } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
        };

        Runnable fielding = () -> {
            try {
                cricket.fielding();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        };

        Thread bowl = new Thread(bowling);
        Thread bat = new Thread(batting);
        Thread field = new Thread(fielding);

        bowl.start();
        bat.start();
        field.start();
    }
}


