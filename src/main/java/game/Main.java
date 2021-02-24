package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Timer;

public class Main extends Application implements EventHandler<ActionEvent> {
    private static final int NODES = 6;
    Button quit;
    Button[][] matrix;
    Timer timer = new Timer();
    String[] values = new String[] {
            "E9", "55", "55", "7A", "BD", "1C"
    };


    @Override
    public void start(Stage primaryStage) {
        GridPane base = new GridPane();
        quit = new Button("Quit");

        quit.setOnAction(this);

        matrix = new Button[NODES][NODES];
        for(int y = 0; y < NODES; y++)
        {
            for(int x = 0; x < NODES; x++)
            {
                Random rand = new Random();
                int rand1 = rand.nextInt(values.length);

                matrix[x][y] = new Button();
                matrix[x][y].setText(values[rand1]);

                base.add(matrix[x][y], y, x);

                matrix[x][y].setOnAction(this);
                //matrix[x][y].setDisable(true);
            }
        }
/*

        Parent base = FXMLLoader.load(getClass().getResource("main.fxml"));
*/
        SubScene matrixScene = new SubScene(base, 200, 200);
        SubScene othersScene = new SubScene(quit, 50, 25);
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(matrixScene, othersScene);
        Scene scene = new Scene(root, 640, 480);

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
    }
}
