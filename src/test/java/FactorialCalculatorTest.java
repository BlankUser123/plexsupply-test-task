import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculatorTest {

    @Test
    public void calculate() {
        Integer[] validData = IntStream.range(0, 100).boxed().toArray(Integer[]::new);
        Arrays.stream(validData).forEach(value -> Assertions.assertEquals(this.factorial(value), new FactorialCalculator().calculate(value), String.valueOf(value)));
    }

    private int factorial(int number) {
        int result = 1;

        for (int i = 1; i <= number; i++) {
            result *= i;
        }

        return result;
    }
}