package game;

import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GridLogic {

    private final GridPane GRID_PANE;
    private final int GRID_DIMENSION;

    public GridLogic(GridPane gridPane, int gridDimension) {

        this.GRID_PANE = gridPane;
        this.GRID_DIMENSION = gridDimension;

        initializeGrid();
    }

    private void initializeGrid() {

        GRID_PANE.getColumnConstraints().clear();
        GRID_PANE.getRowConstraints().clear();

        GRID_PANE.setHgap(5);
        GRID_PANE.setVgap(5);

        GRID_PANE.setPadding(new Insets(5, 5, 5, 5));

        //Adds the columns and rows depending on the grid dimension
        for (int i = 0; i < GRID_DIMENSION; i++) {
            GRID_PANE.getColumnConstraints().add(new ColumnConstraints());
            GRID_PANE.getRowConstraints().add(new RowConstraints());
        }
    }

}
