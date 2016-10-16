/**
 * Created by user on 16.10.2016.
 */

import java.util.*;
import java.math.BigDecimal; //—неизменяемый класс, позволяющий работать с знаковыми десятичными числами произвольной точности.
import java.math.BigInteger; //— неизменяемый класс, для работы с целыми числами.


public class PrimeNumbersResearcher {
    private BigInteger _maxNumber;
    private BigInteger _sqrtFromMaxNumber;

    public PrimeNumbersResearcher(BigInteger maxNumber) throws Exception {
        _maxNumber = maxNumber;
        _sqrtFromMaxNumber = BigRoot.roots(maxNumber, 2);
    }
    static void run (){
        System.out.println("Prime_Numbers_Researcher is running ...");
    }
}