

/**
 * Created by user on 16.10.2016.
 */

import java.math.BigInteger;

public class BigRoot {
    public static BigInteger roots(BigInteger number, int power) throws Exception
    {
        Double d = number.doubleValue();
        BigInteger x1;
        if(Double.isInfinite(d)) {
            String strapprox = number.toString();
            x1 = new BigInteger(strapprox.substring(0, strapprox.length()/7));
        } else {
            String strapprox = String.valueOf(Math.exp(Math.log(number.doubleValue())/power));
            x1 = new BigInteger(strapprox.substring(0, strapprox.indexOf('.')));
        }
        BigInteger x2 = number.divide(x1.pow(power-1));
        switch (x1.compareTo(x2)) {
            case 0: return x1;
            case 1: {BigInteger w = x1;
                x2 = x1;
                x1 = w;
                break;
            }
            default: break;
        }
        while(x2.subtract(x1).compareTo(BigInteger.ONE) > 0) {
            BigInteger w = x1.add(x2).divide(BigInteger.valueOf(2));
            switch(w.pow(power).compareTo(number)) {
                case -1: x1 = w;
                    break;
                case 0:  return w;
                case 1:  x2 = w;
                    break;
            }
        }
        throw new IllegalAccessException(number.toString());
    }
}
