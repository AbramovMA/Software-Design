package game;

import game.TimerClass;
import game.Puzzles;


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

import java.util.Optional;


public class Main extends Application implements EventHandler<ActionEvent> {
    private static final int NODES = 6;
    private static boolean orientation = true;

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

    Button start;
    Button quit;

    int iSeq = 0;
    boolean success = false;
    boolean fail = false;
    int passSeq = 0;

    private Parent createContent(){
        return null;
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


        buffInfo = new Text("Buffer size is " + ourPuzzle.buffSize +"!");
        input = new Text("");

        //NODES = ourPuzzle.puzzleSize;
        //matrix = new Button[NODES][NODES];
        matrix = new Matrix(NODES, base, this);
        matrix.set_values(ourPuzzle.pickedMatrix);
        /*
        for(int x = 0; x < NODES; x++)
        {
            for(int y = 0; y < NODES; y++)
            {
                matrix[x][y] = new Button();
                matrix[x][y].setText(ourPuzzle.pickedMatrix[x][y]);

                base.add(matrix[x][y], x, y);

                matrix[x][y].setOnAction(this);
                //matrix[x][y].setDisable(true);
            }
        }
        */


        SubScene matrixScene = new SubScene(base, 250, 250);
        SubScene othersScene = new SubScene(quit, 50, 25);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(start,timerLabel,buffInfo,sequence,input,matrixScene,quit,ourPuzzle.buffer.contents);
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
            // use  value
            ourPuzzle.buffer.add_value(value);
            passSeq = currSeq.sequenceProgression(iSeq, ourPuzzle.pickedSequence, value,
                    ourPuzzle.buffer, passSeq);
            System.out.println(passSeq + " this is pass!");
        }

        if(passSeq == 2){  //Winner Winner, Chicken Dinner
            Stage endingStage = new Stage();
            goodJob = new Text("You're a Winner!");

            goodJob.setStroke(Color.GREEN);
            goodJob.setStyle("-fx-font: 50 arial");
            VBox boi = new VBox();
            boi.setAlignment(Pos.CENTER);
            boi.getChildren().addAll(goodJob, quit);
            Scene scene = new Scene(boi, 720, 480);
            endingStage.setResizable(false);
            endingStage.setTitle("Success!");

            endingStage.setScene(scene);
            endingStage.show();


        }

        if(passSeq == 3 || fail == true){ // fail is there in case timer runs out and you can set it as fail = true
            Stage endingStage = new Stage();
            badJob = new Text("Game Over!");

            badJob.setStroke(Color.RED);
            badJob.setStyle("-fx-font: 50 arial");
            VBox boi = new VBox();
            boi.setAlignment(Pos.CENTER);
            boi.getChildren().addAll(badJob, quit);
            Scene scene = new Scene(boi, 720, 480);
            endingStage.setResizable(false);
            endingStage.setTitle("Success!");

            endingStage.setScene(scene);
            endingStage.show();

        }
        if(passSeq == 1){ // this is to pass to the next sequence
            iSeq++;

        }
        // here is nothing


        if (actionEvent.getSource() == quit){
            System.exit(0);
        }
        if (actionEvent.getSource() == start){
            sequence.setText(stringedSeq);
            time.handleTime();
        }
    }
}
