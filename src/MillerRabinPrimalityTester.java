/**
 * Created by user on 16.10.2016.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class MillerRabinPrimalityTester {

    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger THREE = new BigInteger("3");

    private Integer _maxNumber = Integer.MAX_VALUE;
    private Integer _sqrtMaxNumber = new Double(Math.sqrt(_maxNumber)).intValue();
    private int _primeNumbersTotalCount = 0;
    private String _filepath = "D:\\Projects\\JetBrains\\IntelliJ_IDEA_Workspace\\Term_9\\Cryptography_LabWork2\\files\\MRT_primeNumbersList.txt";
/*
    MillerRabinPrimalityTester(){

    }
*/
    public static boolean isProbablePrime(BigInteger n, int k) {
        if (n.compareTo(THREE) < 0)
            return true;
        int s = 0;
        BigInteger d = n.subtract(ONE);
        while (d.mod(TWO).equals(ZERO)) {
            s++;
            d = d.divide(TWO);
        }
        for (int i = 0; i < k; i++) {
            BigInteger a = uniformRandom(TWO, n.subtract(ONE));
            BigInteger x = a.modPow(d, n);
            if (x.equals(ONE) || x.equals(n.subtract(ONE)))
                continue;
            int r = 1;
            for (; r < s; r++) {
                x = x.modPow(TWO, n);
                if (x.equals(ONE))
                    return false;
                if (x.equals(n.subtract(ONE)))
                    break;
            }
            if (r == s) // None of the steps made x equal n-1.
                return false;
        }
        return true;
    }

    private static BigInteger uniformRandom(BigInteger bottom, BigInteger top) {
        Random rnd = new Random();
        BigInteger res;
        do {
            res = new BigInteger(top.bitLength(), rnd);
        } while (res.compareTo(bottom) < 0 || res.compareTo(top) > 0);
        return res;
    }

    public void run(int border) throws FileNotFoundException, IOException, RuntimeException {
        // run with -ea to enable assertions
        /*
        String[] primes = { "1", "3", "3613", "7297",
                "226673591177742970257407", "2932031007403" };
        String[] nonPrimes = { "3341", "2932021007403",
                "226673591177742970257405" };


        for (String p : primes)
            //assert isProbablePrime(new BigInteger(p), k);
            System.out.println(p + " " + isProbablePrime(new BigInteger(p), k));
        for (String n : nonPrimes)
            //assert !isProbablePrime(new BigInteger(n), k);
            System.out.println(n + " " + isProbablePrime(new BigInteger(n), k));
            */
        System.out.println("Prime Numbers in range [0.." + _maxNumber + "]");
        long startTime = System.nanoTime();
        int k = 10;
        FileWriter filewriter = new FileWriter(new File(_filepath));
        for(int i=border; i < _maxNumber/100; i++){
            if(isProbablePrime(new BigInteger(Integer.toString(i)), k)){
                //FileWorker.writeFile(_filepath, i);
                //System.out.println(i);
                _primeNumbersTotalCount++;
            }
        }
        filewriter.flush();
        filewriter.close();
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Всего чисел в заданном диапазоне: " + _primeNumbersTotalCount);
        System.out.println("Atkin's Test for Prime Numbers: Time(in sec) = " + (double)estimatedTime/1000000000);
    }
}
