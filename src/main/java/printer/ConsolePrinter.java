package printer;

import calculator.FactorialCalculator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsolePrinter implements Printer {

    @Override
    public void print(Object element) {
        if (element instanceof String) {
            log.info(String.valueOf(element));
            return;
        }

        log.info("{} = {}", element, new FactorialCalculator().calculate((Integer) element));
    }

    @Override
    public void close() {

    }
}
