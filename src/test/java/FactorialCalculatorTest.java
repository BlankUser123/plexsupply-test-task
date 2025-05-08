import calculator.FactorialCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

class FactorialCalculatorTest {

    @Test
    public void calculate() {
        Integer[] validData = IntStream.range(0, 100).boxed().toArray(Integer[]::new);
        Arrays.stream(validData).forEach(value -> Assertions.assertEquals(this.factorial(value), new FactorialCalculator().calculate(value), String.valueOf(value)));
    }

    private BigInteger factorial(int number) {
        BigInteger result = BigInteger.ONE;

        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }
}