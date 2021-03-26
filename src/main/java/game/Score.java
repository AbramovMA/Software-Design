package game;

public class Score extends Main{
    int score = 0;
    int step;

    int getScore(){
        return score;
    }

    void printScore() { System.out.println(score); }

    void handleScore(){
        if(timerLabel.getText() == "90"){
            step = 100;
        }
        if(timerLabel.getText() == "80"){
            step = 90;
        }
        if(timerLabel.getText() == "70"){
            step = 80;
        }
        if(timerLabel.getText() == "60"){
            step = 70;
        }
        if(timerLabel.getText() == "50"){
            step = 60;
        }
        if(timerLabel.getText() == "40"){
            step = 50;
        }
        if(timerLabel.getText() == "30"){
            step = 40;
        }
        if(timerLabel.getText() == "20"){
            step = 30;
        }
        if(timerLabel.getText() == "10"){
            step = 20;
        }

        score += step;
        step = 0;
    }

}
