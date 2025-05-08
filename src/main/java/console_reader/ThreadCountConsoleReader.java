package console_reader;

import java.util.Scanner;

public class ThreadCountConsoleReader implements ConsoleReader {

    private static final Scanner scanner = new Scanner(System.in); // спільний інстанс

    @Override
    public int read() {
        System.out.print("Enter number of threads: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next(); // skip invalid input
        }
        return scanner.nextInt();
    }
}
