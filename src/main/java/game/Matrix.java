package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.Optional;

final public class Matrix{
    public final Button[][] button_grid;
    private final int size;
    private Orientation orientation;

    private enum Orientation {
        horizontal, vertical;

        Orientation opposite() {
            if (this == horizontal)
                return vertical;
            else
                return horizontal;
        }
    }

    /**
     * Matrix constructor
     * requires a matrix size, a grid container and a handler for buttons
     **/
    Matrix(int new_size, GridPane base, EventHandler<ActionEvent> handler, Sequence seq) {
        // set default values
        size = new_size;
        button_grid = new Button[new_size][new_size];
        orientation = Orientation.horizontal;

        for (int column = 0; column < size; ++column)
            for (int row = 0; row < size; ++row) {
                button_grid[column][row] = new Button();
                base.add(button_grid[column][row], column, row); // add to screen
                button_grid[column][row].setOnAction(handler);   // add button press detection
                int finalColumn = column;
                int finalRow = row;
                button_grid[column][row].setOnMouseEntered(e -> {seq.colourfulSequence  (button_grid[finalColumn][finalRow].getText());});
                button_grid[column][row].setOnMouseExited (e -> {seq.uncolourfulSequence(button_grid[finalColumn][finalRow].getText());});
            }

        update_availability(0, 0); // create the initial set of available tiles
    }

    /**
     * Initializes the text on the buttons from a 2D array of labels of appropriate size
     **/
    public void set_values(String[][] values) {
        for (int column = 0; column < size; ++column)
            for (int row = 0; row < size; ++row)
                button_grid[column][row].setText(values[column][row]);
    }

    /**
     * Updates the buttons such that the user cannot press
     * the buttons which are not available to them
     **/
    private void update_availability(int selected_column, int selected_row) {
        for (int column = 0; column < size; column++)
            for (int row = 0; row < size; row++) {
                //button_grid[column][row].setStyle("-fx-background-color: #ff0000");
                button_grid[column][row].setDisable(true);
            }

        if (orientation == Orientation.horizontal)
            for (int column = 0; column < size; column++) {
                //button_grid[column][selected_row].setStyle("-fx-background-color: #00ff00");
                button_grid[column][selected_row].setDisable(false);
            }
        else
            for (int row = 0; row < size; row++) {
                //button_grid[selected_column][row].setStyle("-fx-background-color: #00ff00");
                button_grid[selected_column][row].setDisable(false);
            }

        orientation = orientation.opposite();
    }

    /**
     * Returns the coordinates of the button if a button press was registered by `actionEvent`
     * Returns `Optional.empty` otherwise
     **/
    private Optional<Point> get_selected_position(ActionEvent actionEvent) {
        for (int column = 0; column < size; column++)
            for (int row = 0; row < size; row++)
                if (actionEvent.getSource() == button_grid[column][row])
                    return Optional.of(new Point(column, row));

        return Optional.empty();
    }

    /**
     * Returns the text on the button if a button press was registered by `actionEvent`
     * Returns `Optional.empty` otherwise
     **/
    public Optional<String> get_selected_value(ActionEvent actionEvent) {
        Optional<Point> selected_position = get_selected_position(actionEvent);
        if (selected_position.isPresent()) {
            Point position = selected_position.get();
            int selected_column = position.x;
            int selected_row = position.y;

            update_availability(selected_column, selected_row);
            return Optional.of(button_grid[selected_column][selected_row].getText());
        } else
            return Optional.empty();
    }

    private Optional<Point> get_highlighted_position(MouseEvent mouseEvent) {
        for (int column = 0; column < size; column++)
            for (int row = 0; row < size; row++)
                if (mouseEvent.getSource() == button_grid[column][row])
                    return Optional.of(new Point(column, row));

        return Optional.empty();
    }

    public String get_highlighted_value(MouseEvent mouseEvent) {
        Optional<Point> selected_position = get_highlighted_position(mouseEvent);
        if (selected_position.isPresent()) {
            Point position = selected_position.get();
            int selected_column = position.x;
            int selected_row = position.y;

            update_availability(selected_column, selected_row);
            return button_grid[selected_column][selected_row].getText();
        } else
            return "";
    }
//    final String HOVERED_BUTTON_STYLE = "-fx-background-color: #ffd700;";
//
//    public void mouse_interaction(MouseEvent mouseEvent){
//    button_grid[1][2].
//        for(int i = 0; i < size; i++){
//            for(int j = 0; j < size; j++){
//                if(mouseEvent.getSource() == button_grid[i][j]){
//                    final int boi = i;
//                    final int girl = j;
//                    button_grid[i][j].setOnMouseEntered(e -> button_grid[boi][girl].setStyle(HOVERED_BUTTON_STYLE));//ColourfulSequence,
//                }
//                else{
//                    final int boi = i;
//                    final int girl = j;
//                    button_grid[i][j].setOnMouseExited(e -> button_grid[boi][girl].setStyle());//ColourfulSequence,
//                }
//            }
//        }
//    }
/*
    buttongrid.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
          if(iSeq>0)
          currSeq.colourfulSequence(updateSequence, get_selected_value, sequenceFlow);
          else{
                    currSeq.colourfulSequence(ourPuzzle.pickedSequence, get_highlighted_value(evt), sequenceFlow);


          }
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        if(iSeq>0)
        currSeq.uncolourfulSequence(updateSequence, get_selected_value, sequenceFlow);
        else{
                    currSeq.uncolourfulSequence(ourPuzzle.pickedSequence, get_highlighted_value(evt), sequenceFlow);

          }

    }
});

 */

}