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

        /*
            //System.out.println("Pass 00");

        puzzleGenerate(rng.nextInt(5) + 1;);
        */

        matrix_size = 6; // may be changed to random
        sequenceSize = 5 + new Random().nextInt(5);
        buffSize = (int)(sequenceSize * 1.2) + 1;
        pickedSequence = new String[sequenceSize];
        generate_random_game(matrix_size, sequenceSize);
    }

    private void puzzleGenerate(int i){
        matrix_size = 6;
        if(i == 1){
            System.out.println("i = 1");
            buffSize = 5;
            sequenceSize = 4;
            //puzzleSize = 6;

            //System.out.println("Pass 2");

            String[] newSequence = {"BD", "E9", "BD", "1C"};
            //System.out.println("Pass 3");
            pickedSequence = newSequence;

            String[][] NewMatrix =  {{"E9","7A","1C","1C","55","E9"},
                    {"55","55","7A","7A","E9","BD"},
                    {"1C","E9","55","1C","55","BD"},
                    {"BD","7A","1C","55","7A","55"},
                    {"BD","1C","7A","55","E9","7A"},
                    {"1C","E9","55","55","7A","E9"}};
            pickedMatrix = NewMatrix;
            //System.out.println("Pass4");
        }
        else if (i == 2){
            System.out.println("i = 2");
            buffSize = 6;
            sequenceSize = 5;
            //puzzleSize = 6;


            String[] newSequence = {"E9", "7A", "1C", "55", "55"};
            pickedSequence = newSequence;

            String[][] NewMatrix = {{"E9","55","7A","1C","7A","1C"},
                    {"BD","1C","BD","E9","55","55"},
                    {"1C","7A","E9","E9","E9","7A"},
                    {"E9","7A","7A","E9","BD","BD"},
                    {"1C","E9","1C","BD","7A","7A"},
                    {"55","1C","55","7A","BD","BD"}};
            pickedMatrix = NewMatrix;
        }
        else if(i == 3){
            System.out.println("i = 3");
            buffSize = 8;
            sequenceSize = 6;
            //puzzleSize = 6;


            String[] newSequence = {"7A", "55", "1C", "E9", "BD", "7A"};
            pickedSequence = newSequence;

            String[][] NewMatrix = {{"1C","55","BD","1C","BD","55"},
                    {"E9","7A","55","E9","E9","BD"},
                    {"1C","1C","7A","E9","BD","1C"},
                    {"55","E9","55","55","BD","1C"},
                    {"E9","1C","BD","E9","E9","55"},
                    {"7A","E9","55","55","55","7A"}};
            pickedMatrix = NewMatrix;
        }
        else if(i == 4){
            System.out.println("i = 4");
            buffSize = 10;
            sequenceSize = 8;
            //puzzleSize = 6;


            String[] newSequence = {"BD", "55", "BD", "E9", "7A", "1C", "1C", "E9"};
            pickedSequence = newSequence;

            String[][] NewMatrix = {{"BD","7A","7A","55","1C","55"},
                    {"E9","55","E9","7A","55","E9"},
                    {"1C","BD","E9","BD","E9","BD"},
                    {"7A","1C","55","7A","E9","55"},
                    {"7A","1C","55","BD","BD","E9"},
                    {"1C","55","E9","E9","E9","55"}};
            pickedMatrix = NewMatrix;
        }
        else if(i == 5){
            System.out.println("i = 5");
            buffSize = 10;
            sequenceSize = 7;
           // puzzleSize = 6;


            String[] newSequence = {"1C", "BD", "55", "E9", "7A", "55", "1C"};
            pickedSequence = newSequence;

            String[][] NewMatrix = {{"7A","1C","BD","1C","7A","55"},
                    {"E9","7A","1C","7A","1C","BD"},
                    {"BD","E9","BD","55","BD","E9"},
                    {"E9","BD","E9","7A","1C","E9"},
                    {"7A","55","BD","E9","7A","1C"},
                    {"55","7A","1C","BD","E9","7A"}};
            pickedMatrix = NewMatrix;
        }
        else{
            System.out.println("i = Error");
            sequenceSize = 3;
            pickedSequence = new String[] {"ER", "RO", "R!"};
        }
    }

    public void generate_random_game(int matrix_size, int sequence_size){
        String[][] matrix_values = new String[matrix_size][matrix_size];

        // generate matrix values
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
