package game;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Sequence extends Main {


    String[] sequence;
    TextFlow colourSequence;
    public enum SequencePassState{nothing, pass, winner, loser};

    public SequencePassState sequenceProgression(int i, String[] sequence, String input,
                                       Buffer buffer){
        SequencePassState state;
        String[] currentSequence;
        currentSequence = sequence;
        if(i == (sequence.length - 1)){
            if(input == currentSequence[i]){//Winner
                state = SequencePassState.winner;
            }
            else{

                if(buffer.is_full()){//Game Over
                    state = SequencePassState.loser;
                    // this is when the game should end, cause he didn't pass the last
                    // value of the sequence
                }
                else{
                    state = SequencePassState.nothing; // nothing
                }
            }
        }
        else{

            if(buffer.is_full()){//Game Over
                state = SequencePassState.loser;

            }
            else if(input == currentSequence[i]){
                state = SequencePassState.pass; // pass

            }
            else{

                state = SequencePassState.nothing; //nothing really. Player has to chose another one
            }
        }
        return state;
    }

    public String[] arrayRemove(String[] sequence, int count){
        String [] updateSequence = new String[sequence.length - (count)];
        for(int i = 0; i < updateSequence.length; i++){
            updateSequence[i] = sequence[i+count];
        }
        return updateSequence;

    }

    //this function announces game over
    public void getGameOver(Button quit){
        Stage endingStage = new Stage();
        Text badJob = new Text("Game Over!");

        badJob.setStroke(Color.RED);
        badJob.setStyle("-fx-font: 50 arial");

        VBox endingBox = new VBox();
        endingBox.setAlignment(Pos.CENTER);
        endingBox.getChildren().addAll(badJob, quit);

        Scene scene = new Scene(endingBox, 720, 480);
        endingStage.setResizable(false);
        endingStage.setTitle("Game Over!");

        endingStage.setScene(scene);
        endingStage.show();
    }

    //this function announces that player won
    public void getWinner(Button quit,int score){
        scoreLabel = new Label("SCORE: " + Integer.toString(score));
        scoreLabel.setTextFill(Color.YELLOWGREEN);
        scoreLabel.setFont(Font.font(40));

        Stage endingStage = new Stage();
        Text goodJob = new Text("You're a Winner!");

        goodJob.setStroke(Color.GREEN);
        goodJob.setStyle("-fx-font: 50 arial");

        VBox endingBox = new VBox();
        endingBox.setAlignment(Pos.CENTER);
        endingBox.getChildren().addAll(scoreLabel,goodJob, quit);

        Scene scene = new Scene(endingBox, 720, 480);
        endingStage.setResizable(false);
        endingStage.setTitle("Victory!");

        endingStage.setScene(scene);
        endingStage.show();
    }


    public void colourfulSequence(String value){
        Text partOfTheSeq;
        Text emptySpace;
        colourSequence.getChildren().clear();
        for(int i = 0; i < sequence.length; i++){
            partOfTheSeq = new Text(sequence[i]);
            emptySpace = new Text(" ");
            partOfTheSeq.setFill(Color.BLACK);
            if(sequence[i] == value){
                partOfTheSeq.setFill(Color.RED);
            }
            colourSequence.getChildren().add(partOfTheSeq);
            colourSequence.getChildren().add(emptySpace);
        }

    }
    public void uncolourfulSequence(String value){
        Text partOfTheSeq;
        Text emptySpace;
        colourSequence.getChildren().clear();
        for(int i = 0; i < sequence.length; i++){
            partOfTheSeq = new Text(sequence[i]);
            emptySpace = new Text(" ");
            partOfTheSeq.setFill(Color.BLACK);
            colourSequence.getChildren().add(partOfTheSeq);
            colourSequence.getChildren().add(emptySpace);
        }

    }
//    @Override
//    public void handle(ActionEvent actionEvent){
//        if(ActionEvent.getSource() == quit){
//            System.exit(0);
//        }
//    }

}

