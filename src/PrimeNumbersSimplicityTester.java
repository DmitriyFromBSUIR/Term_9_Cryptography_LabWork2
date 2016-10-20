import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by user on 17.10.2016.
 */
public class PrimeNumbersSimplicityTester {
    private Integer _maxNumber = Integer.MAX_VALUE;
    private Integer _sqrtMaxNumber = new Double(Math.sqrt(_maxNumber)).intValue();
    private int _value1 = 0;
    private int _value2 = 0;
    private int [] _arr = {2,3,5,7};
    private int _primeNumbersTotalCount = 4;
    private String _filepath = "D:\\Projects\\JetBrains\\IntelliJ_IDEA_Workspace\\Term_9\\Cryptography_LabWork2\\files\\primeNumbersList.txt";
    private int [][] _primeNumbersValuesAndMarkersTable;

    public PrimeNumbersSimplicityTester(){
        //createSieveOfEratosthenes();
        System.out.println("Max int value (32-bit) = " + _maxNumber);
        System.out.println("Sqrt from Max int value (32-bit) = " + _sqrtMaxNumber);
        /*for(int i=0; i < 4; i++) {
            System.out.println(_arr[i]);
        }*/
        createSieveOfEratosthenes();
    }

    public void fillUpSieveOfEratosthenes() {
        for (int i=11; i < _sqrtMaxNumber; i++) {
            if(_primeNumbersValuesAndMarkersTable[i][1] == 1) {
                for (int j=11; i*j < _sqrtMaxNumber; j++) {
                    _primeNumbersValuesAndMarkersTable[i*j][1] = 0;
                }
            }
        }
        //System.out.println("Number --- marker " + curNumber + " " + primes[curNumber]);
        //return primes[curNumber];
    }

    public void createSieveOfEratosthenes(){
        _primeNumbersValuesAndMarkersTable = new int [_sqrtMaxNumber][2];
        for(int i=0; i < _arr.length; i++){
            _primeNumbersValuesAndMarkersTable[_arr[i]][0] = _arr[i];
        }
        for(int i=0; i < _sqrtMaxNumber; i++){
            _primeNumbersValuesAndMarkersTable[i][1] = 1;
        }
        //
        fillUpSieveOfEratosthenes();
    }

    public void displaySieveOfEratosthenes(){
        for(int i=0; i < _sqrtMaxNumber; i++){
            if(_primeNumbersValuesAndMarkersTable[i][1] == 1){
                System.out.println(_primeNumbersValuesAndMarkersTable[i][0]);
            }
        }
    }

/*
    public void generateAndDisplay() throws FileNotFoundException, IOException, RuntimeException{
        long startTime = System.nanoTime();
        //for(int i=2; i < _sqrtMaxNumber; i++){
        //Math.sqrt(
        //for(int i=2; i < _maxNumber/6; i++){
        FileWriter filewriter = new FileWriter(new File(_filepath));
        for(int i=2; i < 200000/6; i++){
            _value1 = 6*i + 1;
            _value2 = 6*i - 1;
            if( fillUpSieveOfEratosthenes(_value2) ) {
                filewriter.write(_value2 + " \r\n");
                _primeNumbersTotalCount++;


                //System.out.println(_value2);
                //System.out.println(_value1);
                //FileWorker.writeFile(_filepath, _value2);
                //FileWorker.writeFile(_filepath, _value1);

            }
            if( fillUpSieveOfEratosthenes(_value1) ) {
                filewriter.write(_value1 + " \r\n");
                _primeNumbersTotalCount++;
            }
            //System.out.println(new Double(Math.pow(_value2, 2)).intValue());
            //System.out.println(new Double(Math.pow(_value1, 2)).intValue());
        }
        filewriter.flush();
        filewriter.close();
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Всего чисел в заданном диапазоне: " + _primeNumbersTotalCount);
        System.out.println("Atkin's Test for Prime Numbers: Time(in sec) = " + (double)estimatedTime/1000000000);

    }
    */
}
