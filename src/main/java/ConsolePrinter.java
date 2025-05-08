public class ConsolePrinter implements Printer {

    @Override
    public void print(Object element) {
        if (element instanceof String) {
            System.out.println(element);
            return;
        }

        System.out.println(element + " = " + new FactorialCalculator().calculate((Integer) element));
    }
}
