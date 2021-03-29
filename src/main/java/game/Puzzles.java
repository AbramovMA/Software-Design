package game;
import java.util.Arrays;
import java.util.Vector;
import java.util.Random;

public class Puzzles {
    int matrix_size;
    int buffSize;
    int sequenceSize;
    String[][] pickedMatrix;
    String[] pickedSequence;

    private Random rng;

    private String[] value_set = {"1C", "55", "7A", "BD", "E9"};


    public void puzzleGenerator(){
        rng = new Random();

        matrix_size = 6; // may be changed to random
        sequenceSize = 5 + new Random().nextInt(5);
        buffSize = (int)(sequenceSize * 1.2) + 1;
        pickedSequence = new String[sequenceSize];
        generate_random_game(matrix_size, sequenceSize);
    }


    public void generate_random_game(int matrix_size, int sequence_size){
        String[][] matrix_values = new String[matrix_size][matrix_size];

        for (int i = 0; i < matrix_values.length; ++i)
            for (int j = 0; j < matrix_values.length; ++j)
                matrix_values[i][j] = value_set[rng.nextInt(value_set.length)];

        // generate goal sequence
        Vector<String> sequence_values = new Vector<String>();
        java.util.Set<java.awt.Point> selected_cells = new java.util.HashSet();

        int x = 0, y = 0;
        boolean is_x = true;
        while (sequence_values.size() < sequence_size){
            if (is_x)
                x = rng.nextInt(matrix_size);
            else
                y = rng.nextInt(matrix_size);
            if (!selected_cells.contains(new java.awt.Point(x,y))) {
                sequence_values.add(matrix_values[x][y]);
                selected_cells.add(new java.awt.Point(x,y));
                is_x = !is_x;
            }
        }

        // set-up
        pickedMatrix = matrix_values;
        sequence_values.toArray(pickedSequence);
    }


}
