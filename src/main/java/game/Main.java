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

    //HoverEffect hover;
    TimerClass time;
    Sequence currSeq;
    Label timerLabel;
    TextFlow sequenceFlow;

    Label scoreLabel;


    Text sequence;
    Text input;
    Text buffInfo;

    Matrix matrix;

//    Text goodJob;

    Text badJob;

    FileChooser fileChooser;
    CustomPuzzle custom;
    Puzzles ourPuzzle;
    Buffer buffer;

    Button start;
    Button quit;
    Button quit2;
    Button openButton;

    int iSeq = 0;

    Sequence.SequencePassState seqState = Sequence.SequencePassState.nothing;

    //VBox root;


    VBox ingame;
    VBox timeUp;
    VBox[] scenes;
    StackPane root;

    Timeline initTimeLine;



    boolean goal_reachable = true;
    String[] updateSequence;

    public void getGameOver() {
        Stage endingStage = new Stage();
        badJob = new Text("Game Over!");


        VBox endingBox = new VBox();
        endingBox.setAlignment(Pos.CENTER);
        endingBox.getChildren().addAll(scoreLabel, badJob, quit2);
    }


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

        currSeq = new Sequence();

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

        //TextFlow initialisation
        sequence = new Text("Press start to show sequence");
        sequenceFlow = new TextFlow(sequence);


        custom = new CustomPuzzle();

        ourPuzzle = new Puzzles();
        ourPuzzle.puzzleGenerator();
        currSeq.sequenceNew = ourPuzzle.pickedSequence;
        currSeq.colourSequence = sequenceFlow;

        buffer = new Buffer(ourPuzzle.buffSize);

        buffInfo = new Text("Buffer size is " + ourPuzzle.buffSize +"!");
        input = new Text("");

        matrix = new Matrix(matrix_size, base, this, currSeq);
        matrix.set_values(ourPuzzle.pickedMatrix);

        SubScene matrixScene = new SubScene(base, 250, 200);
        ////////////////////matrixScene.setVisible(false);
        //SubScene othersScene = new SubScene(quit, 50, 25);

        scene.setAlignment(Pos.CENTER);
        //It didn't want to be centered
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
        //String[] updatedSequence = ourPuzzle.pickedSequence; //this is for visuals

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
                    //root.setAlignment(Pos.BOTTOM_RIGHT);
                }

            seqState = currSeq.sequenceProgression(iSeq, ourPuzzle.pickedSequence, value,
                    buffer);
//                String suka = String.join(" ", ourPuzzle.pickedSequence);
//            System.out.println(suka);
//                System.out.println(ourPuzzle.pickedSequence[0]+ourPuzzle.pickedSequence[1]);
            if(seqState == Sequence.SequencePassState.pass){
                System.out.println("Hello 1");
            }
            if(seqState == Sequence.SequencePassState.winner){
                System.out.println("Hello 2");

            }
            if(seqState == Sequence.SequencePassState.loser){
                System.out.println("Hello 3");

            }
            if(seqState == Sequence.SequencePassState.nothing){
                System.out.println("Hello 0");

            }

        }


        switch (seqState){
            case winner:
                currSeq.getWinner(quit,handleScore());
                break;

            case loser:
                currSeq.getGameOver(quit);
                break;

            case pass:
                iSeq++;
                //visuals
                updateSequence = currSeq.arrayRemove(ourPuzzle.pickedSequence, iSeq);
                currSeq.sequenceNew = updateSequence;
                break;


            //visuals
            //this is a value removal, that works (was before I decided to make hover highlighter)
            //updateSequence = currSeq.arrayRemove(ourPuzzle.pickedSequence, iSeq);
            //System.out.println("Updated: " + updateSequence);

            //////TESTING HIGHLIGHT
//            String something = currSeq.colourfulSequence(updateSequence, "E9");
//            sequence.setText(something);
            //sequenceFlow = new TextFlow(currSeq.colourfulSequence(updateSequence, "E9"));
            //currSeq.colourfulSequence(updateSequence, "E9", sequenceFlow);

//            System.out.println(nom);
//            sequence.setText(nom.getText());
            //////End of TEST is here

            //updateSequence[1].setColor(Color.YELLOWGREEN);

//            stringedSeq = String.join(" ", updateSequence);
//            sequence.setText(stringedSeq);
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


//        if(mouseEvent == hover){
//
//        }
        /*
        if(actionEvent.getSource() == hover){
            if(iSeq > 0){
                //String hoho = matrix.get_selected_value();
                // get value from the matrix thing
                //hover highlight is Text  with hoho,
                //hover highlight function with updateSequence
                //This String/Text = currSeq.colourfulSequence(updateSequence, hoho);
                // sequence.setText(highlightedSequence);
                currSeq.colourfulSequence(updateSequence, "E9", sequenceFlow);


            }
            else{
                //String hoho = matrix.get_selected_value();
                //hover highlight function with ourPuzzle.pickedSequence
                 currSeq.colourfulSequence(updateSequence, "E9", sequenceFlow);
            }
        }

         */
        if (actionEvent.getSource() == openButton){
            configureFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                custom.loadPuzzle(file);
                matrix.set_values(custom.customMatrix);
                currSeq.sequenceNew = custom.goalSequence;
                ourPuzzle.pickedSequence = Arrays.copyOf(custom.goalSequence, custom.goalSequence.length);
                buffer = new Buffer(currSeq.sequenceNew.length + 3);
                buffInfo.setText("Buffer size is " + (currSeq.sequenceNew.length + 3) +"!");
                ingame.getChildren().remove(7);
                ingame.getChildren().add(7, buffer.contents);

            }
        }
    }


    int handleScore(){
        int score = 0;

        int seconds = Integer.parseInt(timerLabel.getText());

        if(seconds > 90) {
            System.out.println("between 90-100  |  finish time: " + timerLabel.getText());
            score = 100;
        }

        if(seconds > 80 && seconds < 90) {
            System.out.println("between 80-90  |  finish time: " + timerLabel.getText());
            score = 90;
        }

        if(seconds > 70 && seconds < 80) {
            System.out.println("between 70-80  |  finish time: " + timerLabel.getText());
            score = 80;
        }

        if(seconds > 60 && seconds < 70) {
            System.out.println("between 60-70  |  finish time: " + timerLabel.getText());
            score = 70;
        }

        if(seconds > 50 && seconds < 60) {
            System.out.println("between 50-60  |  finish time: " + timerLabel.getText());
            score = 60;
        }

        if(seconds > 40 && seconds < 50) {
            System.out.println("between 40-50  |  finish time: " + timerLabel.getText());
            score = 50;
        }

        if(seconds > 30 && seconds < 40) {
            System.out.println("between 30-40  |  finish time: " + timerLabel.getText());
            score = 40;
        }

        if(seconds > 20 && seconds < 30) {
            System.out.println("between 20-30  |  finish time: " + timerLabel.getText());
            score = 30;
        }

        if(seconds > 10 && seconds < 20) {
            System.out.println("between 10-20  |  finish time: " + timerLabel.getText());
            score = 20;
        }

        if(seconds > 0 && seconds < 10) {
            System.out.println("between 0-10  |  finish time: " + timerLabel.getText());
            score = 10;
        }

        System.out.println("score : " + score);
        return score;
    }

}