package game;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Sequence {

    String[] sequence;
    TextFlow colourSequence;

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
                if(buffer.is_full()){//THIS IS OUT OF MEMORY, MAXIM
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
            if(buffer.is_full()){//THIS IS OUT OF MEMORY TOO, MAXIM
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
    public void getWinner(Button quit){
        Stage endingStage = new Stage();
        Text goodJob = new Text("You're a Winner!");

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

    //public void hoverOverValue()
    /*
    functions:
    hover listener
    get value from the button and highlight the sequence for the test
    the text editor: TextFlow and text. Each text is going to be added to the TextFlow and
    each text will have different color, if the mouse was hovered over that value
     */

    //I've tried void, String(for testing and it works), TextFlow and Text.
    public void colourfulSequence(String[] sequence, String value, TextFlow colourSequence){
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
    public void uncolourfulSequence(String[] sequence, String value, TextFlow colourSequence){
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

