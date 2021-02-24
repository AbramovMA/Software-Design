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

public class Main extends Application implements EventHandler<ActionEvent> {
    public static void main (String[] args){
        launch(args);
    }

    Button quitButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cyberpunk mini game");

        quitButton = new Button("Quit");

        quitButton.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(quitButton);

        Scene scene = new Scene(layout,300, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == quitButton){
            System.exit(0);
        }
    }
     //test
}
