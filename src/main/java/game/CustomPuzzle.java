package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomPuzzle {
    String[][] customMatrix;
    String[] goalSequence;
    public void loadPuzzle(File file) {

        try {
            Scanner sizeScanner = new Scanner(file);
            String[] sizeTemp = sizeScanner.nextLine().split(" ");
            sizeScanner.close();

            int matrixSize = sizeTemp.length;

            Scanner matrixScanner = new Scanner(file);

            customMatrix = new String[matrixSize][matrixSize];

            for (int i = 0; i < matrixSize; i++) {
                String[] numbers = matrixScanner.nextLine().split(" ");
                for (int j = 0; j < matrixSize; j++) {
                    customMatrix[j][i] = numbers[j];
                    System.out.println(customMatrix[j][i]);
                }
            }
            while (matrixScanner.hasNextLine()) {
                String[] sequence = matrixScanner.nextLine().split(" ");
                int seqSize = sequence.length;
                goalSequence = new String[seqSize];
                System.out.println(seqSize);
                for (int j = 0; j < seqSize; j++) {
                    goalSequence[j] = sequence[j];
                    System.out.println(" " + goalSequence[j]);
                }
            }
            matrixScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
