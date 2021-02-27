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

import java.util.Random;


public class Main extends Application implements EventHandler<ActionEvent> {
    private int NODES = 6;
    Text timeString;
    Text sequence;
    Text input;
    Text buffInfo;
    Button[][] matrix;

    Puzzles ourPuzzle = new Puzzles();
    TimerClass timerClass = new TimerClass();
    String[] values = new String[] {
            "E9", "55", "55", "7A", "BD", "1C"
    };
    Button quit;

    private Parent createContent(){
        return null;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane base = new GridPane();
        quit = new Button("Quit");

        ourPuzzle.puzzleGenerator();
        String stringedSeq = String.join(" ", ourPuzzle.pickedSequence);
        timeString = new Text(Integer.toString(timerClass.getTime()));

        sequence = new Text("Desired sequence: " + stringedSeq);
        buffInfo = new Text("Buffer size is " + ourPuzzle.buffSize +"!");
        input = new Text("");

        NODES = ourPuzzle.puzzleSize;
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
/*

        Parent base = FXMLLoader.load(getClass().getResource("main.fxml"));
*/
        SubScene matrixScene = new SubScene(base, 250, 250);
        SubScene othersScene = new SubScene(quit, 50, 25);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(timeString,sequence,buffInfo,input,matrixScene, othersScene);
        Scene scene = new Scene(root, 720, 480);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Cyberpunk Breach");
        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
//        for(int y = 0; y < NODES; y++) {
//            for (int x = 0; x < NODES; x++) {
//                if (actionEvent.getSource() == matrix[x][y]) {
//                    System.out.println(matrix[x][y].getText());
//                }
//            }
//        }
        if (actionEvent.getSource() == quit){
            System.exit(0);
        }

        for(int y = 0; y < NODES; y++) {
            for (int x = 0; x < NODES; x++) {
                if (actionEvent.getSource() == matrix[x][y]) {
                    input.setText(matrix[x][y].getText());
                }
            }
        }
    }
}
