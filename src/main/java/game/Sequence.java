package game;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Sequence {


    //This function decides if the player progresses in the game
    public int sequenceProgression(int i, String[] sequence, String input,
                                       Buffer buffer, int pass){
    // 0- nothing, 1 - pass, 2 - success, 3 - fail
        String[] currentSequence = new String[sequence.length];
        currentSequence = sequence;
        if(i == (sequence.length - 1)){
            if(input == currentSequence[i]){
                pass = 2; // success
            }
            else{
                if(buffer.is_full()){
                    pass = 3; // we trigger Game Over
                    // this is when the game should end, cause he didn't pass the last
                    // value of the sequence
                }
                else{
                    pass = 0; // nothing
                }
            }
        }
        else{
            if(buffer.is_full()){
                pass = 3;

            }
            else if(input == currentSequence[i]){
                //i++;
                pass = 1; // pass

            }
            else{
                pass = 0; //nothing really. Player has to chose another one
            }
        }
        return pass;
    }

    public String[] arrayRemove(String[] sequence, int count){
        String [] updateSequence = new String[sequence.length - (count)];
        for(int i = 0; i < updateSequence.length; i++){
            updateSequence[i] = sequence[i+count];
        }
        return updateSequence;

    }

    //this function announces game over
    public void getGameOver(Text badJob, Button quit){
        Stage endingStage = new Stage();
        badJob = new Text("Game Over!");

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
    public void getWinner(Text goodJob, Button quit){
        Stage endingStage = new Stage();
        goodJob = new Text("You're a Winner!");

        goodJob.setStroke(Color.GREEN);
        goodJob.setStyle("-fx-font: 50 arial");

        VBox endingBox = new VBox();
        endingBox.setAlignment(Pos.CENTER);
        endingBox.getChildren().addAll(goodJob, quit);

        Scene scene = new Scene(endingBox, 720, 480);
        endingStage.setResizable(false);
        endingStage.setTitle("Victory!");

        endingStage.setScene(scene);
        endingStage.show();
    }

}
