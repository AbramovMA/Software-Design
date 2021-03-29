package game;

import java.applet.Applet;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.*;


public class Main extends Application implements EventHandler<ActionEvent> {
    private static final int matrix_size = 6;
    private Stage stage;

    TimerClass time;
    Sequence sequenceData;
    Label timerLabel;
    TextFlow sequenceFlow;

    Label scoreLabel;


    Text sequence;
    Text input;
    Text buffInfo;

    Matrix matrix;

    FileChooser fileChooser;
    CustomPuzzle custom;
    Puzzles ourPuzzle;
    Buffer buffer;

    Button start;
    Button quit;
    Button quit2;
    Button openButton;

    int amountOfCorrectValues = 0;

    Sequence.SequencePassState seqState = Sequence.SequencePassState.nothing;

    VBox ingame;
    VBox timeUp;
    VBox[] scenes;
    StackPane root;

    Timeline initTimeLine;


    boolean goal_reachable = true;
    String[] updateSequence;


    @Override
    public void start(Stage primaryStage) {
        ingame = ingameScene();
        timeUp = timeUpScene();
        scenes = new VBox[]{ingame, timeUp};
        root = new StackPane(scenes[0]);

        initTimeLine = new Timeline();

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Cyberpunk Breach");
        primaryStage.setScene(scene);
        primaryStage.show();
        this.stage = primaryStage;
    }

    public VBox ingameScene(){
        VBox scene = new VBox();

        GridPane base = new GridPane();

        sequenceData = new Sequence();

        time = new TimerClass();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open");

        openButton = new Button("Open");
        openButton.setOnAction(this);

        timerLabel = new Label(Integer.toString(time.getStartTime()));
        timerLabel.setTextFill(Color.BLUE);
        timerLabel.setFont(Font.font(30));
        timerLabel.textProperty().bind(time.getTimeSeconds().asString());

        start = new Button("Start");
        start.setOnAction(this);

        quit = new Button("Quit");
        quit.setOnAction(this);

        sequence = new Text("Press start to show sequence");
        sequenceFlow = new TextFlow(sequence);


        custom = new CustomPuzzle();

        ourPuzzle = new Puzzles();
        ourPuzzle.puzzleGenerator();
        sequenceData.sequenceNew = ourPuzzle.pickedSequence;
        sequenceData.colourSequence = sequenceFlow;

        buffer = new Buffer(ourPuzzle.buffSize);

        buffInfo = new Text("Buffer size is " + ourPuzzle.buffSize +"!");
        input = new Text("");

        matrix = new Matrix(matrix_size, base, this, sequenceData);
        matrix.set_values(ourPuzzle.pickedMatrix);

        SubScene matrixScene = new SubScene(base, 250, 200);
        matrixScene.setVisible(false);


        scene.setAlignment(Pos.CENTER);
        sequenceFlow.setTextAlignment(TextAlignment.CENTER);
        scene.getChildren().addAll(openButton,start,timerLabel,buffInfo,sequenceFlow,input,matrixScene,buffer.contents,quit);

        return scene;
    }


    public VBox timeUpScene() {

        VBox scene = new VBox();

        GridPane base = new GridPane();

        quit2 = new Button("Quit");
        quit2.setOnAction(this);

        Text badJob = new Text("time is up!");

        badJob.setStroke(Color.RED);
        badJob.setStyle("-fx-font: 50 arial");

        scene.setAlignment(Pos.CENTER);
        scene.getChildren().addAll(badJob, quit2);


        return scene;
    }



    public static void main(String[] args) {
        launch(args);
    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("Select Your Puzzle TXT");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text file", "*.txt")
        );
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        String stringedSeq = String.join(" ", ourPuzzle.pickedSequence);

        Optional<String> selected_matrix_value = matrix.get_selected_value(actionEvent);

        if (selected_matrix_value.isPresent()){
            String value = selected_matrix_value.get();

            buffer.add_value(value);
            if (goal_reachable) // test if game over is inevitable
                if (buffer.unreachable(ourPuzzle.pickedSequence)) {
                    goal_reachable = false;
                    Text kek = new Text("You bad!");
                    kek.setFill(Color.INDIANRED);
                    ingame.getChildren().add(kek);
                }

            seqState = sequenceData.sequenceProgression(amountOfCorrectValues, ourPuzzle.pickedSequence, value,
                    buffer);
        }


        switch (seqState){
            case winner:
                sequenceData.getWinner(quit,handleScore());
                break;

            case loser:
                sequenceData.getGameOver(quit);
                break;

            case pass:
                amountOfCorrectValues++;
                //visuals
                updateSequence = sequenceData.arrayRemove(ourPuzzle.pickedSequence, amountOfCorrectValues);
                sequenceData.sequenceNew = updateSequence;
                break;
        }

        if (actionEvent.getSource() == quit){
            System.exit(0);
        }

        if (actionEvent.getSource() == quit2){
            System.exit(0);
        }


        if (actionEvent.getSource() == start){
            scenes[0].getChildren().get(7).setVisible(true);
            sequence.setText(stringedSeq);
            Stage startStage = new Stage();
            ingame.getChildren().get(6).setVisible(true);
            time.handleTime();

            start.setVisible(false);

            initTimeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(time.getStartTime()),
                    (ActionEvent event) -> {
                        if (root.getChildren().get(0).equals(scenes[0]))//If the first scene is loaded, load the second scene.
                        {
                            root.getChildren().set(0, scenes[1]);
                        }
                    }));

            initTimeLine.setCycleCount(Timeline.INDEFINITE);
            initTimeLine.play();
            Scene scene = new Scene(root, 720, 480);
            startStage.setResizable(false);
            startStage.setTitle("Cyberpunk Breach");
            startStage.setScene(scene);
            startStage.show();
        }

        if (actionEvent.getSource() == openButton){
            configureFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                custom.loadPuzzle(file);
                matrix.set_values(custom.customMatrix);
                sequenceData.sequenceNew = custom.goalSequence;
                ourPuzzle.pickedSequence = Arrays.copyOf(custom.goalSequence, custom.goalSequence.length);
                buffer = new Buffer(sequenceData.sequenceNew.length + 3);
                buffInfo.setText("Buffer size is " + (sequenceData.sequenceNew.length + 3) +"!");
                ingame.getChildren().remove(7);
                ingame.getChildren().add(7, buffer.contents);
            }
        }
    }


    int handleScore(){
        int score = 0;

        int seconds = Integer.parseInt(timerLabel.getText());

        if(seconds > 90) {
            //System.out.println("between 90-100  |  finish time: " + timerLabel.getText());
            score = 100;
        }

        if(seconds > 80 && seconds < 90) {
            //System.out.println("between 80-90  |  finish time: " + timerLabel.getText());
            score = 90;
        }

        if(seconds > 70 && seconds < 80) {
            //System.out.println("between 70-80  |  finish time: " + timerLabel.getText());
            score = 80;
        }

        if(seconds > 60 && seconds < 70) {
            //System.out.println("between 60-70  |  finish time: " + timerLabel.getText());
            score = 70;
        }

        if(seconds > 50 && seconds < 60) {
            //System.out.println("between 50-60  |  finish time: " + timerLabel.getText());
            score = 60;
        }

        if(seconds > 40 && seconds < 50) {
            //System.out.println("between 40-50  |  finish time: " + timerLabel.getText());
            score = 50;
        }

        if(seconds > 30 && seconds < 40) {
            //System.out.println("between 30-40  |  finish time: " + timerLabel.getText());
            score = 40;
        }

        if(seconds > 20 && seconds < 30) {
            //System.out.println("between 20-30  |  finish time: " + timerLabel.getText());
            score = 30;
        }

        if(seconds > 10 && seconds < 20) {
            //System.out.println("between 10-20  |  finish time: " + timerLabel.getText());
            score = 20;
        }

        if(seconds > 0 && seconds < 10) {
            //System.out.println("between 0-10  |  finish time: " + timerLabel.getText());
            score = 10;
        }

        System.out.println("score : " + score);
        return score;
    }

}