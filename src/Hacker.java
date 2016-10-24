import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Dmitry on 23.10.2016.
 */


public class Hacker {
    // class for cracking results
    //private class DecodeResult
    public class DecodeResult
    {
        public int _A;
        public int _B;
        public int _Alpha;
        public int _Beta;
        public int[] _Word;

        DecodeResult(int a, int b, int alpha, int beta, int[] resultWord){
            _A = a;
            _B = b;
            _Alpha = alpha;
            _Beta = beta;
            _Word = resultWord;
        }
    }
    // number module for calculation
    private int _p;
    // the list of cracked transfers
    public LinkedList<DecodeResult> _decodeResults;
    public LinkedList<DecodeResult> getDecodedResults(){
        return _decodeResults;
    }
    private void setDecodedResults(LinkedList<DecodeResult> newDecodeResults){
        _decodeResults = newDecodeResults;
    }
    //

    public Hacker(int p, int[] muWord) {
        _p = p;
        _decodeResults = new LinkedList<DecodeResult>();

        //List<int> _primeNumbersTable = PrimeNumbers.SieveOfEratosthenes(p);
        AtkinsPrimalityTester sieveOfAtkin = new AtkinsPrimalityTester(p);
        ArrayList<Integer> _primeNumbersTable = sieveOfAtkin.getPrimeNumbersTable();

        HashSet<Integer> remaindersSet = new HashSet<Integer>();

        int primeNumbersCount = _primeNumbersTable.size();
        for (int a = 0; a < primeNumbersCount - 1; a++) {
            for (int b = 0; b < primeNumbersCount - 1; b++) {

                int alpha = EncryptedTransmission.FindAlphaBeta(_primeNumbersTable.get(a), p);
                if (alpha == 0)
                    continue;
                int beta = EncryptedTransmission.FindAlphaBeta(_primeNumbersTable.get(b), p);
                if (beta == 0)
                    continue;
                int remainder = (_primeNumbersTable.get(a) * _primeNumbersTable.get(b)) % (p - 1);
                if (remaindersSet.add(remainder) == false)
                    continue;

                int[] resultWord = FindFromMu2(muWord, _primeNumbersTable.get(a), _primeNumbersTable.get(b));
                //int[] resultWord = FindFromMu3(mu_word, primeNumbers[b]);

                DecodeResult decodeResult = new DecodeResult(_primeNumbersTable.get(a), _primeNumbersTable.get(b), alpha, beta, resultWord);
                _decodeResults.addLast(decodeResult);
            }
        }
    }

    private int[] FindFromMu2(int[] word, int a, int b)
    {
        int[] wordMu3 = EncryptedTransmission.FindEncrWord(word, a, _p);
        int[] wordMu4 = EncryptedTransmission.FindEncrWord(wordMu3, b, _p);
        return wordMu4;
    }

    private int[] FindFromMu3(int[] word, int exp)
    {
        int[] wordMu4 = EncryptedTransmission.FindEncrWord(word, exp, _p);
        return wordMu4;
    }

}
