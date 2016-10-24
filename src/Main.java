import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws Exception, FileNotFoundException, IOException, RuntimeException {
        System.out.println("Simulation is running ...");

        //
        int p = 23;
        int a = 5;
        int b = 7;

        // generate prime numbers
        AtkinsPrimalityTester primeNumbers = new AtkinsPrimalityTester(p);

        // message transfering
        int [] msg = {17,0};
        EncryptedTransmission encryptedTransmission = new EncryptedTransmission(p, a, b, msg);
        int[] mu2Word = encryptedTransmission.getMu2();

        // cracking
        Hacker decoder = new Hacker(p, mu2Word);

        // get msg
        LinkedList<Hacker.DecodeResult> result = decoder.getDecodedResults();
        System.out.println("Finished");

        //LinkedList<DecodeResult> decodeResults = decode.DecodeResults;

        //long maxUint32 = 4294967295;
        //int border = 4194304;
        //System.out.println("max unsigned int value = 4294967296");

        //PrimeNumbersResearcher.run();
        //PrimeNumbersTester primeNumbers = new PrimeNumbersTester();
        //primeNumbers.displayPrimeNumbers();

        //AtkinsPrimalityTester primeNumbers = new AtkinsPrimalityTester(131);
        //primeNumbers.displayPrimeNumbers();

        //MillerRabinPrimalityTester.run(maxUint32);

        //MillerRabinPrimalityTester primeNumbers = new MillerRabinPrimalityTester();
        //primeNumbers.run(border);

        //PrimeNumbersSimplicityTester primeNumbers = new PrimeNumbersSimplicityTester();
        //--primeNumbers.generateAndDisplay();
        //primeNumbers.displaySieveOfEratosthenes();
    }
}
