package game;

import game.TimerClass;
import game.Puzzles;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.KeyValue;
import javafx.util.Duration;

public class Main extends Application implements EventHandler<ActionEvent> {
    private static final int NODES = 6;
    private static boolean orientation = true;

    private static final Integer STARTTIME = 15;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    Text sequence;
    Text input;
    Text buffInfo;
    Button[][] matrix;

    Puzzles ourPuzzle = new Puzzles();
    TimerClass timerClass = new TimerClass();
    String[] values = new String[] {
            "E9", "55", "55", "7A", "BD", "1C"
    };

    Button start;
    Button quit;

    private Parent createContent(){
        return null;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane base = new GridPane();

        timerLabel = new Label(Integer.toString(STARTTIME));
        timerLabel.setTextFill(Color.BLUE);
        timerLabel.setFont(Font.font(30));
        timerLabel.textProperty().bind(timeSeconds.asString());

        start = new Button("Start");
        start.setOnAction(this);

        quit = new Button("Quit");
        quit.setOnAction(this);

        sequence = new Text("Press start to show sequence");

        ourPuzzle.puzzleGenerator();
        String stringedSeq = String.join(" ", ourPuzzle.pickedSequence);


        buffInfo = new Text("Buffer size is " + ourPuzzle.buffSize +"!");
        input = new Text("");

        //NODES = ourPuzzle.puzzleSize;
        matrix = new Button[NODES][NODES];
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

        SubScene matrixScene = new SubScene(base, 250, 250);
        SubScene othersScene = new SubScene(quit, 50, 25);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(start,timerLabel,buffInfo,sequence,input,matrixScene,ourPuzzle.buffer.contents,quit);
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
        for (int c = 0; c < NODES; c++) { //for resetting the matrix style after each click
            for (int r = 0; r < NODES; r++) {
                matrix[r][c].setStyle(null);
            }
        }
        for (int column = 0; column < NODES; column++) { //scans each row/column
            for (int row = 0; row < NODES; row++) {
                matrix[row][column].setStyle(null);
                if (actionEvent.getSource() == matrix[row][column] && !orientation) { //vertically color white with !orientation
                    for (column = 0; column < NODES; column++) {
                        matrix[row][column].setStyle("-fx-background-color: #ffffff");
                        orientation = true;
                    }
                    return;
                }
                else if (actionEvent.getSource() == matrix[row][column] && orientation) { //horizontally color white after orientation set to true
                    for (row = 0; row < NODES; row++) {
                        matrix[row][column].setStyle("-fx-background-color: #ffffff");
                        orientation = false;
                    }
                    return;
                }
            }
        }
        if (actionEvent.getSource() == quit){
            System.exit(0);
        }
        if (actionEvent.getSource() == start){
            sequence.setText("Desired sequence: BD,E9,55,7A");
            if (timeline != null) {
                timeline.stop();
            }
            timeSeconds.set(STARTTIME);
            timeline = new Timeline();
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(STARTTIME+1),
                            new KeyValue(timeSeconds, 0)));
            timeline.playFromStart();
        }

        ourPuzzle.buffer.update();

    }
}
