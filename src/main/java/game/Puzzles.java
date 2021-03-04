package game;
import java.util.Random;

public class Puzzles {
    String[] pickedSequence = {};
    String[][] pickedMatrix = {};
    int buffSize = 0;
    int sequenceSize = 0;
    //int puzzleSize = 0;
    Random randomPuzzle = new Random();
    int randomNum = 0;


    public void puzzleGenerator(){
        //System.out.println("Pass 00");

        randomNum = randomPuzzle.nextInt(5) + 1;

        //System.out.println("Pass 1");
        puzzleGenerate(randomNum);
    }

    private void puzzleGenerate(int i){
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





}
