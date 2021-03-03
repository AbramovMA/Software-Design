package game;


import java.util.Timer;
import java.util.TimerTask;

public class timeThread extends Main {
    Timer timer;

    public timeThread(int seconds) {
        timer = new Timer();
        timer.schedule(new timeOver(),seconds * 1000);
    }

    class timeOver extends TimerTask {
        public void run() {
            System.out.println("time is up!");
            timer.cancel(); // Terminate the timer thread
            System.exit(0);
        }
    }
}
