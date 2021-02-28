package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.Optional;

final public class Matrix {
    private final Button[][] button_grid;
    private final int size;
    private boolean orientation;

    Matrix(int new_size, GridPane base, EventHandler<ActionEvent> eh){
        size        = new_size;
        button_grid = new Button[new_size][new_size];
        orientation = true;

        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j) {
                button_grid[i][j] = new Button();
                base.add(button_grid[i][j], i, j);
                button_grid[i][j].setOnAction(eh);
            }
    }

    public void set_values(String[][] values){
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                button_grid[i][j].setText(values[i][j]);
    }

    private void reset_style(){
        for (int column = 0; column < size; column++) {
            for (int row = 0; row < size; row++) {
                button_grid[row][column].setStyle(null);
            }
        }
    }

    public Optional<String> get_selected_value(ActionEvent actionEvent){
        boolean has_selected  = false;
        int selected_row      = -1;
        int selected_column   = -1;

        for (int column = 0; column < size; column++) {
            for (int row = 0; row < size; row++) {
                if(actionEvent.getSource() == button_grid[row][column]) {
                    has_selected    = true;
                    selected_row    = row;
                    selected_column = column;
                }
            }
        }

        if(has_selected) {
            reset_style();

            if (orientation) {
                for (int row = 0; row < size; row++) {
                    button_grid[row][selected_column].setStyle("-fx-background-color: #ffffff");
                }
            } else {
                for (int column = 0; column < size; column++) {
                    button_grid[selected_row][column].setStyle("-fx-background-color: #ffffff");
                }
            }

            orientation = !orientation;

            return Optional.of(button_grid[selected_row][selected_column].getText());
        } else
            return Optional.empty();
    }
}
