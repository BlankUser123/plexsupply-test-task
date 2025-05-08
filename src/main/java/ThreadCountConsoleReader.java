import java.io.IOException;
import java.util.Scanner;

public class ThreadCountConsoleReader implements ConsoleReader {

    @Override
    public int read() {
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextInt();
        }
    }
}
