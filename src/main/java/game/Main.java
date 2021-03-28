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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

    Score score;
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
    Button openButton;

    int iSeq = 0;

    //boolean victory = false;
    //boolean gameOver = false;
    Sequence.SequencePassState seqState = Sequence.SequencePassState.nothing;

    VBox root;

    boolean goal_reachable = true;
    String[] updateSequence;

    public void getGameOver() {
        Stage endingStage = new Stage();
        badJob = new Text("Game Over!");


        VBox endingBox = new VBox();
        endingBox.setAlignment(Pos.CENTER);
        endingBox.getChildren().addAll(scoreLabel, badJob, quit);
    }


    @Override
    public void start(Stage primaryStage) {

        this.stage = primaryStage;

        GridPane base = new GridPane();

        score = new Score();

        currSeq = new Sequence();

        time = new TimerClass();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open");

        openButton = new Button("Open");
        openButton.setOnAction(this);

        scoreLabel = new Label(Integer.toString(score.score));
        scoreLabel.setTextFill(Color.YELLOWGREEN);
        scoreLabel.setFont(Font.font(25));

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
        currSeq.sequence = ourPuzzle.pickedSequence;
        currSeq.colourSequence = sequenceFlow;

        buffer = new Buffer(ourPuzzle.buffSize);

        buffInfo = new Text("Buffer size is " + ourPuzzle.buffSize +"!");
        input = new Text("");

        matrix = new Matrix(matrix_size, base, this, currSeq);
        matrix.set_values(ourPuzzle.pickedMatrix);

        SubScene matrixScene = new SubScene(base, 250, 250);
        SubScene othersScene = new SubScene(quit, 50, 25);

        root = new VBox();
        root.setAlignment(Pos.CENTER);
        //It didn't want to be centered
        sequenceFlow.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().addAll(openButton,scoreLabel,start,timerLabel,buffInfo,sequenceFlow,input,matrixScene,quit,buffer.contents);
        Scene scene = new Scene(root, 720, 480);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Cyberpunk Breach");
        primaryStage.setScene(scene);
        primaryStage.show();
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
                    root.getChildren().add(new Text("You bad!"));
                }
            seqState = currSeq.sequenceProgression(iSeq, ourPuzzle.pickedSequence, value,
                    buffer);          
        }

        switch (seqState){
            case winner:
                currSeq.getWinner(quit);
                break;

            case loser:
                currSeq.getGameOver(quit);
                break;

            case pass:
                iSeq++;
                //visuals
                updateSequence = currSeq.arrayRemove(ourPuzzle.pickedSequence, iSeq);
                currSeq.sequence = updateSequence;
                break;


        }

        if (actionEvent.getSource() == quit){
            System.exit(0);
        }

        if (actionEvent.getSource() == start){
            sequence.setText(stringedSeq);
            time.handleTime();
            new timeThread(time.getStartTime());
            start.setVisible(false);
        }

        if (actionEvent.getSource() == openButton){
            configureFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                custom.loadPuzzle(file);
            }
        }
    }
}