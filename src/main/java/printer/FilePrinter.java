package printer;

import calculator.FactorialCalculator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FilePrinter implements Printer {

    private final Writer writer;

    public FilePrinter() {
        try {
            this.writer = new FileWriter("output.txt", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void print(Object element) {
        try {
            if (element instanceof String) {
                writer.write(String.valueOf(element));
                writer.write("\n");
                writer.flush();
                return;
            }

            writer.write(element + " = " + new FactorialCalculator().calculate((Integer) element));
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}