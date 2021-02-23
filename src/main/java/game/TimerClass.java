package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class TimerClass{
    int seconds = 60;
    int signal;
    int time;
    // 0-success; 1-ran out of time; 2-failed complete; 3-quitted;
    boolean timeStartStop = false;
    boolean success = false;
    boolean fail = false;
    boolean ranOutOfTime = false;


    //  NEED TO ADD IT TO THE SCENE, WHERE IT WOULD CHANGE the number each time
    public void getTime(){
        time = gameLaunched();
        //return time;
    } // this needs some changes probably

    /*


     */

    private int gameLaunched(){
        timeStartStop = true;
        return gameTimer();
    }

    private int gameTimer(){
        if(ranOutOfTime == true){
            timeStartStop = false;
            signal = 1;
        }
        // fail is true can happen only by another part of the game
        if(fail == true){
            timeStartStop = false;
            signal = 2;
        }
        //same thing as fail code
        if(success == true){
            timeStartStop = false;
            signal = 0;
        }
        if(timeStartStop = true){
            if(seconds <= 0){
                ranOutOfTime = true;
                signal = 1;
            }
            else{
                seconds--;
                //print?
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameTimer();
            }
        }

        return signal;
    }

}
