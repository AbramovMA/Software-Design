package game;
import java.util.Random;

public class Puzzles {
    //we need to make a array of matrices
    //random generator
    //sections
    String[] pickedSequence = {};
    String[][] pickedMatrix = {};
    int buffSize;
    int sequenceSize;
    Random randomPuzzle;
    int randomNum;


    public void puzzleGenerator(){
        randomNum = randomPuzzle.nextInt(2) + 1;
        puzzleGenerate(randomNum);
    }

    private void puzzleGenerate(int i){
        if(i == 1){
            buffSize = 6;
            sequenceSize = 4;
            String[] newSequence = {"BD", "E9", "BD", "1C"};
            pickedSequence = newSequence;
            String[][] NewMatrix = {{"E9","7A","1C","1C","55","E9"},
                    {"55","55","7A","7A","E9","BD"},
                    {"1C","E9","55","1C","55","BD"},
                    {"BD","7A","1C","55","7A","55"},
                    {"BD","1C","7A","55","E9","7A"},
                    {"1C","E9","55","55","7A","E9"}};
            pickedMatrix = NewMatrix;
        }
        if (i == 2){
            buffSize = 6;
            sequenceSize = 5;
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
        if(i == 3){
            buffSize = 8;
            sequenceSize = 6;
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
        else{
            sequenceSize = 3;
            String[] newSequence = {"ER", "RO", "R!"};
        }
    }





}
