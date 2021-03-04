package game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.util.*;


public class TimerClass extends Main{
    static final int STARTTIME = 100;
    Timeline timeline;
    IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    boolean timeGameOver = false;


    public int getStartTime(){
        return STARTTIME;
    }

    public IntegerProperty getTimeSeconds(){
        return timeSeconds;
    }

    public void handleTime(){
        if (timeline != null) {
            timeline.stop();
        }

        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME+1),
                        new KeyValue(timeSeconds, 0)));
        timeline.play();
    }

}
