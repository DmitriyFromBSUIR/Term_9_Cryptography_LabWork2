import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, RuntimeException {
        System.out.println("Simulation is running ...");
        //long maxUint32 = 4294967295;
        int border = 4194304;
        System.out.println("max unsigned int value = 4294967296");
        //PrimeNumbersResearcher.run();
        //PrimeNumbersTester primeNumbers = new PrimeNumbersTester();
        //primeNumbers.displayPrimeNumbers();

        //AtkinsPrimalityTester primeNumbers = new AtkinsPrimalityTester();
        //primeNumbers.displayPrimeNumbers();

        //MillerRabinPrimalityTester.run(maxUint32);
        MillerRabinPrimalityTester primeNumbers = new MillerRabinPrimalityTester();
        primeNumbers.run(border);
        //PrimeNumbersSimplicityTester primeNumbers = new PrimeNumbersSimplicityTester();
        //--primeNumbers.generateAndDisplay();
        //primeNumbers.displaySieveOfEratosthenes();
    }
}
