/**
 * Created by Dmitry on 16.10.2016.
 *
 *
 * Решето Аткина — алгоритм поиска простых чисел.
 Описание алгоритма:
 Алгоритм состоит из следующих шагов.
 1. Выписываются все натуральные числа из диапазона от 1 до n.

 2. Перебираются все возможные пары чисел x, y, где x<=sqrt(n) и y<=sqrt(n). Т.е. (1,1), (1,2),…, (1,sqrt(n)), (2,1), (2,2),…, (sqrt(n),sqrt(n)).

 3. Для каждой пары чисел вычисляются значения следующих трех уравнений:

 a) 4*x^2+y^2;
 b) 3*x^2+y^2;
 c) 3*x^2-y^2, значение вычисляется только при x>y.

 4. Для каждого вычисленного значения уравнений вычисляются остатки от деления на 12, причем

 a) если остаток равен 1 или 5 для значения первого уравнения;
 b) если остаток равен 7 для значения второго уравнения;
 с) если остаток равен 11 для значения третьего уравнения.

 То в исходном ряду чисел от 1 до n число помечается как простое.
 Замечание: если какое-то число Z присутствует в значениях нескольких уравнений (допустим a и b), и остаток от деления на 12 этого числа удовлетворяет условиям обоих групп, то число помечается два раза: сначала как простое, а потом как составное.

 5. На последнем этапе проверяется кратность помеченных чисел квадратам простых чисел из диапазона от 5 до sqrt(n). Если число кратно квадрату, то оно помечается как составное.
 */

import java.util.*;

public class AtkinsPrimalityTester{
    private Integer _maxNumber = Integer.MAX_VALUE;
    private int _maxInt16bit = 4194304;
    private Integer _sqrtFromMaxNumber = new Double(Math.sqrt(_maxNumber)).intValue();
    private int _sqrtFromMaxInt16bit = new Double(Math.sqrt(_maxInt16bit)).intValue();
    // Initialization the Sieve of Atkin
    //private boolean [] _isPrime = new boolean[_maxNumber];
    private boolean [] _isPrime = new boolean[_maxInt16bit];
    //
    private int _x2 = 0;
    //
    private int _y2 = 0;
    //
    private int _n = 0;
    // global counter for prime numbers
    private int _primeNumbersTotalCount = 3;

    public AtkinsPrimalityTester() {
        // measure time
        // nanoTime() --- Returns the current value of the running Java Virtual Machine's high-resolution time source, in nanoseconds.
        long startTime = System.nanoTime();

        _isPrime[2] = true;
        _isPrime[3] = true;

        // Предположительно простые — это целые с нечётным числом
        // представлений в данных квадратных формах.
        // x2 и y2 — это квадраты i и j (оптимизация).
        for (int i = 1; i <= _sqrtFromMaxInt16bit; i++) {
            _x2 += 2 * i - 1;
            _y2 = 0;
            for (int j = 1; j <= _sqrtFromMaxInt16bit; j++) {
                _y2 += 2 * j - 1;
                _n = 4 * _x2 + _y2;
                if ((_n <= _maxInt16bit) && (_n % 12 == 1 || _n % 12 == 5))
                    _isPrime[_n] = !_isPrime[_n];

                // n = 3 * x2 + y2;
                _n -= _x2; // Оптимизация
                if ((_n <= _maxInt16bit) && (_n % 12 == 7))
                    _isPrime[_n] = !_isPrime[_n];

                // n = 3 * x2 - y2;
                _n -= 2 * _y2; // Оптимизация
                if ((i > j) && (_n <= _maxInt16bit) && (_n % 12 == 11))
                    _isPrime[_n] = !_isPrime[_n];
            }
        }
        // Отсеиваем кратные квадратам простых чисел в интервале [5, sqrt(limit)].
        // (основной этап не может их отсеять)
        for (int i = 5; i <= _sqrtFromMaxInt16bit; i++) {
            if (_isPrime[i]) {
                _n = i * i;
                for (int j = _n; j <= _maxInt16bit; j += _n) {
                    _isPrime[j] = false;
                }
            }
        }
        // measure end of time
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Atkin's Test for Prime Numbers: Time(in sec) = " + (double)estimatedTime/1000000000);
    }

    // Вывод списка простых чисел в консоль.
    public void displayPrimeNumbers(){
        System.out.println("Max int value (32-bit) = " + _maxNumber);
        System.out.println("Prime Numbers in range [0.." + _maxInt16bit + "]");
        System.out.println("2");
        System.out.println("3");
        System.out.println("5");
        for (int i = 6; i < _maxInt16bit; i++) {  // добавлена проверка делимости на 3 и 5. В оригинальной версии алгоритма потребности в ней нет.
            //if ((_isPrime[i]) && (i % 3 != 0) && (i % 5 !=  0)){
            if ((_isPrime[i]) && (i % 3 != 0) && (i % 5 !=  0)){
                System.out.println(i);
                _primeNumbersTotalCount++;
            }
        }
        //double percent = _primeNumbersTotalCount/
        System.out.println("Всего чисел в заданном диапазоне: " + _primeNumbersTotalCount);
    }
}
