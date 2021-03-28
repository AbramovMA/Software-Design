package game;

import java.applet.Applet;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.*;


public class Main extends Application implements EventHandler<ActionEvent> {
    private static final int matrix_size = 6;

    //HoverEffect hover;
    TimerClass time;
    Sequence currSeq;
    Label timerLabel;
    TextFlow sequenceFlow;
    Text sequence;
    Text input;
    Text buffInfo;

    Matrix matrix;

//    Text goodJob;
//    Text badJob;

    Puzzles ourPuzzle;
    Buffer buffer;

    Button start;
    Button quit;

    int iSeq = 0;
    //boolean victory = false;
    //boolean gameOver = false;
    int passSeq = 0;

    String[] updateSequence;




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

        //TextFlow initialisation
        sequence = new Text("Press start to show sequence");
        sequenceFlow = new TextFlow(sequence);

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

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        //It didn't want to be centered
        sequenceFlow.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().addAll(start,timerLabel,buffInfo,sequenceFlow,input,matrixScene,quit,buffer.contents);
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
        //tring[] updatedSequence = ourPuzzle.pickedSequence; //this is for visuals

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
            currSeq.getWinner(quit);
            //primaryStage.close();
        }

        if(passSeq == 3){ //Game Over
            // gameOver is there in case timer runs out and you can set it as gameOver = true
            System.out.println(passSeq + ": Game Over");
            currSeq.getGameOver(quit);
            //primaryStage.close();
        }
        if(passSeq == 1){ // this is to pass to the next sequence
            System.out.println(passSeq + ": next one.");
            iSeq++;

            //visuals
            //this is a value removal, that works (was before I decided to make hover highlighter)
            updateSequence = currSeq.arrayRemove(ourPuzzle.pickedSequence, iSeq);
            currSeq.sequence = updateSequence;
            System.out.println("Updated: " + updateSequence);

                //////TESTING HIGHLIGHT
//            String something = currSeq.colourfulSequence(updateSequence, "E9");
//            sequence.setText(something);
            //sequenceFlow = new TextFlow(currSeq.colourfulSequence(updateSequence, "E9"));
            //currSeq.colourfulSequence("E9");

//            System.out.println(nom);
//            sequence.setText(nom.getText());
            //////End of TEST is here

            //updateSequence[1].setColor(Color.YELLOWGREEN);

//            stringedSeq = String.join(" ", updateSequence);
//            sequence.setText(stringedSeq);
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
    }
}