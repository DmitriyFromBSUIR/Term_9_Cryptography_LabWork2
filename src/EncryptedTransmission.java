import java.math.BigInteger;

/**
 * Created by Dmitry on 23.10.2016.
 */
public class EncryptedTransmission {
    public int[] mu1_word;
    public int[] getMu1(){
        return mu1_word;
    }
    public void setMu1(int[] mu1_word){
        mu1_word = mu1_word;
    }
    public int[] mu2_word;
    public int[] getMu2(){
        return mu2_word;
    }
    public void setMu2(int[] mu2_word){
        mu2_word = mu2_word;
    }
    public int[] mu3_word;
    public int[] getMu3(){
        return mu3_word;
    }
    public void setMu3(int[] mu3_word){
        mu3_word = mu3_word;
    }

    private int _p;
    private int _a;
    private int _b;

    private int _alpha;
    public int getAlpha(){
        return _alpha;
    }
    public void setAlpha(int alpha){
        _alpha = alpha;
    }

    private int _beta;
    public int getBeta(){
        return _beta;
    }
    public void setBeta(int beta){
        _beta = beta;
    }

    private int[] _originalWord;
    public int[] getOriginalWord(){
        return _originalWord;
    }
    public void setOriginalWord(int[] originalWord){
        _originalWord = originalWord;
    }
/*
    private Integer[] _originalWord;
    public Integer[] getOriginalWord(){
        return _originalWord;
    }
    public void setOriginalWord(Integer[] originalWord){
        _originalWord = originalWord;
    }
*/

    public static boolean compareArrays(int[] array1, int[] array2) {
        boolean result = true;
        if (array1 != null && array2 != null){
            if (array1.length != array2.length)
                result = false;
            else
                for (int i = 0; i < array2.length; i++) {
                    if (array2[i] != array1[i]) {
                        result = false;
                    }
                }
        }else{
            result = false;
        }
        return result;
    }

    public EncryptedTransmission(int p, int a, int b, int[] word) throws Exception
    {
        this._p = p;
        this._a = a;
        this._b = b;
        this._originalWord = word;
        this._alpha = FindAlphaBeta(a, p);
        this._beta = FindAlphaBeta(b, p);

        if(_alpha == 0 || _beta == 0)
            throw new Exception("Alpha = 0 or beta = 0!");

        mu1_word = FindEncrWord(_originalWord, _alpha, p);
        mu2_word = FindEncrWord(mu1_word, _beta, p);
        mu3_word = FindEncrWord(mu2_word, a, p);


        int[] mu4_word = FindEncrWord(mu3_word, b, p);
        //if (_originalWord.SequenceEqual(mu4_word) == false)
        if (compareArrays(_originalWord, mu4_word) == false)
            throw new Exception("Parameters error! Maybe p is not prime number.");
    }

    public static int[] FindEncrWord(int[] word, int exp, int p)
    {
        int[] newWord = new int[word.length];
        for (int i = 0; i < word.length; i++)
            newWord[i] = FindEncrNum(word[i], exp, p);
        return newWord;
    }
    /*
      BigInteger pow(BigInteger base, BigInteger exponent) {
      BigInteger result = BigInteger.ONE;
      while (exponent.signum() > 0) {
        if (exponent.testBit(0)) result = result.multiply(base);
        base = base.multiply(base);
        exponent = exponent.shiftRight(1);
      }
      return result;
    }
     */
/*
    private static int FindEncrNum(int symbol, int exp, int p)
    {
        BigInteger bigSymbol = new BigInteger(Integer.toString(symbol));
        BigInteger Exp = new BigInteger(Integer.toString(exp));
        BigInteger result = bigSymbol.pow(exp);
        //result = result % (new BigInteger(Integer.toString(p)));
        result = result.mod(new BigInteger(Integer.toString(p)));
        return result.intValue();
    }
*/
    private static int FindEncrNum(int symbol, int exp, int p){
        double bigSymbol = symbol;
        double Exp = exp;
        double result = Math.pow(bigSymbol, exp);
        result = result % p;
    }

    //public static int FindAlphaBeta(int num, int p)
    public static int FindAlphaBeta(Integer num, Integer p)
    {
        for (int i = 1; i < p - 1; i++)
        {
            if (((i * num) % (p - 1) ) == 1)
                return i;
        }
        return 0;
    }
}
