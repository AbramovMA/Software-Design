package game;

public class timeThread extends Main implements Runnable{
    @Override
    public void run() {
        try {
            while (time.timeSeconds.getValue() == 0) {
                System.out.println("ZERO HAS NOT BEEN HIT");
            }
        } catch(Exception e){
            }
    }
}
