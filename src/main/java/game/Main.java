package game;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.*;


public class Main extends Application implements EventHandler<ActionEvent> {
    private static final int matrix_size = 6;

    TimerClass time;
    Sequence currSeq;
    Label timerLabel;
    Text sequence;
    Text input;
    Text buffInfo;

    Matrix matrix;

    Text goodJob;
    Text badJob;

    Puzzles ourPuzzle;
    Buffer buffer;

    Button start;
    Button quit;

    int iSeq = 0;
    //boolean victory = false;
    //boolean gameOver = false;
    int passSeq = 0;

    public void getGameOver(){
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

    @Override
    public void start(Stage primaryStage) {
        GridPane base = new GridPane();

        currSeq = new Sequence();

        time = new TimerClass();

        timerLabel = new Label(Integer.toString(time.getStartTime()));
        timerLabel.setTextFill(Color.BLUE);
        timerLabel.setFont(Font.font(30));
        timerLabel.textProperty().bind(time.getTimeSeconds().asString());

        start = new Button("Start");
        start.setOnAction(this);

        quit = new Button("Quit");
        quit.setOnAction(this);

        sequence = new Text("Press start to show sequence");

        ourPuzzle = new Puzzles();
        ourPuzzle.puzzleGenerator();

        buffer = new Buffer(ourPuzzle.buffSize);


        buffInfo = new Text("Buffer size is " + ourPuzzle.buffSize +"!");
        input = new Text("");

        matrix = new Matrix(matrix_size, base, this);
        matrix.set_values(ourPuzzle.pickedMatrix);

        SubScene matrixScene = new SubScene(base, 250, 250);
        SubScene othersScene = new SubScene(quit, 50, 25);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(start,timerLabel,buffInfo,sequence,input,matrixScene,quit,buffer.contents);
        Scene scene = new Scene(root, 720, 480);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Cyberpunk Breach");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String stringedSeq = String.join(" ", ourPuzzle.pickedSequence);

        Optional<String> selected_matrix_value = matrix.get_selected_value(actionEvent);
        if (selected_matrix_value.isPresent()){
            String value = selected_matrix_value.get();

            buffer.add_value(value);
            passSeq = currSeq.sequenceProgression(iSeq, ourPuzzle.pickedSequence, value,
                    buffer, passSeq);
        }

        if(passSeq == 2){  //Winner Winner, Chicken Dinner
            System.out.println(passSeq + ": Winner");

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

        if(passSeq == 3){
            // gameOver is there in case timer runs out and you can set it as gameOver = true
            System.out.println(passSeq + ": Game Over");

            getGameOver();

        }
        if(passSeq == 1){ // this is to pass to the next sequence
            System.out.println(passSeq + ": next one.");
            iSeq++;
            //need to add some visuals, so player could see, on which part
            //of the sequence player is right now

        }
        // here is nothing


        if (actionEvent.getSource() == quit){
            System.exit(0);
        }

        if (actionEvent.getSource() == start){
            sequence.setText(stringedSeq);
            time.handleTime();
            new timeThread(time.getStartTime());
            start.setVisible(false);
        }
    }
}