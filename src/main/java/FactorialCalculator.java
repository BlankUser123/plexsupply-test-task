import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

public class FactorialCalculator implements Calculator {

    @Override
    public int calculate(int number) {
        return IntStream
                .range(1, number + 1)
                .reduce((left, right) -> left * right)
                .orElse(1);
    }
}
