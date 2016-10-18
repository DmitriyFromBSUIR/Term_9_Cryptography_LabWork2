import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, RuntimeException {
        System.out.println("Simulation is running ...");
        //PrimeNumbersResearcher.run();
        //PrimeNumbersTester primeNumbers = new PrimeNumbersTester();
        //primeNumbers.displayPrimeNumbers();

        //AtkinsPrimalityTester primeNumbers = new AtkinsPrimalityTester();
        //primeNumbers.displayPrimeNumbers();
        PrimeNumbersSimplicityTester primeNumbers = new PrimeNumbersSimplicityTester();
        primeNumbers.generateAndDisplay();
    }
}
