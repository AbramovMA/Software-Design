package game;


import java.util.Timer;
import java.util.TimerTask;

public class timeThread extends Main {
    Timer timer;
    boolean timeGameOver = false;

    public timeThread(int seconds) {
        timer = new Timer();
        timer.schedule(new timeOver(), seconds * 1000);

    }

    class timeOver extends TimerTask {
        public void run() {
            System.out.println("time is up!");
           //getGameOver();
            timer.cancel(); // Terminate the timer thread
            timeGameOver = true;
            //getGameOver();
            //System.exit(0);

        }
    }
}
