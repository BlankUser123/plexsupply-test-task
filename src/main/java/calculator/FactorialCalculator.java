package calculator;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class FactorialCalculator implements Calculator {

    @Override
    public BigInteger calculate(int number) {
        return IntStream
                .rangeClosed(1, number)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
