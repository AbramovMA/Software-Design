import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main (String[] args){
        launch(args);
    }

    Button quitButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cyberpunk mini game");

        quitButton = new Button("Quit");

        StackPane layout = new StackPane();
        layout.getChildren().add(quitButton);

        Scene scene = new Scene(layout,300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}