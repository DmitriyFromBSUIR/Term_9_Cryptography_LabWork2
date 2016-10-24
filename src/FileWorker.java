/**
 * Created by Dmitry on 18.10.2016.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWorker {
    //private int _dataPartSize = 0;

    public static void writeFile(String filename, int value) throws FileNotFoundException, IOException, RuntimeException {
        FileWriter filewriter = new FileWriter(new File(filename));
        filewriter.write(value + " ");
        filewriter.write("\n");

        filewriter.flush();
        filewriter.close();
    }

    public static int[] readFile(String filename, int dataPartSize) throws FileNotFoundException, IOException, RuntimeException {
        File file = new File(filename);
        Scanner scannerfile = new Scanner(file);

        int[] primeNumbersSeries = new int[dataPartSize];

        if(dataPartSize > 0) {
            for (int i=0; i < dataPartSize; i++) {
                    if(scannerfile.hasNextInt()) {
                        primeNumbersSeries[i] = scannerfile.nextInt();
                        //System.out.println(matrixData[i][j]);
                    }
            }
            scannerfile.close();
        }
        else {
            scannerfile.close();
            throw new RuntimeException("Error! Incorrect parameter in the function 'readFile'");
        }
        return primeNumbersSeries;
    }
}
