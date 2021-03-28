package game;

public class Score extends Main{
    int score = 0;

    int getScore(){
        return score;
    }

    void printScore() { System.out.println("Current score: " + score); }

    void handleScore(TimerClass time){
        TimerClass time = new TimerClass();

        int step = 0;

        int seconds = Integer.parseInt(time.getTimeSeconds().toString());

        if(seconds > 90) {
            System.out.println("between 90-100  |  finish time: " + timerLabel.getText());
            step = 100;
        }

        if(seconds > 80 && seconds < 90) {
            System.out.println("between 80-90  |  finish time: " + timerLabel.getText());
            step = 90;
        }

        if(seconds > 70 && seconds < 80) {
            System.out.println("between 70-80  |  finish time: " + timerLabel.getText());
            step = 80;
        }

        if(seconds > 60 && seconds < 70) {
            System.out.println("between 60-70  |  finish time: " + timerLabel.getText());
            step = 70;
        }

        if(seconds > 50 && seconds < 60) {
            System.out.println("between 50-60  |  finish time: " + timerLabel.getText());
            step = 60;
        }

        if(seconds > 40 && seconds < 50) {
            System.out.println("between 40-50  |  finish time: " + timerLabel.getText());
            step = 50;
        }

        if(seconds > 30 && seconds < 40) {
            System.out.println("between 30-40  |  finish time: " + timerLabel.getText());
            step = 40;
        }

        if(seconds > 20 && seconds < 30) {
            System.out.println("between 20-30  |  finish time: " + timerLabel.getText());
            step = 30;
        }

        if(seconds > 10 && seconds < 20) {
            System.out.println("between 10-20  |  finish time: " + timerLabel.getText());
            step = 20;
        }

        if(seconds > 0 && seconds < 10) {
            System.out.println("between 0-10  |  finish time: " + timerLabel.getText());
            step = 10;
        }

        score += step;
        scoreLabel.setText(Integer.toString(score));
        System.out.println("score : " + score);
    }

    void testMethod(){
        return;
    }
}
