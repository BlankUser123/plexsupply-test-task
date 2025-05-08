import console_reader.ThreadCountConsoleReader;
import executor.SequencedThreadPoolExecutor;
import file_reader.DataReader;
import printer.ConsolePrinter;
import printer.FilePrinter;
import printer.Printer;
import rate_limiter.RateLimiter;
import rate_limiter.RateLimiterImpl;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;

public class Program {

    public static void main(String[] args) {

        int threadCount = new ThreadCountConsoleReader().read();

        Future<Object[]> futureData;
        try (ExecutorService inputService = Executors.newFixedThreadPool(1)) {

            futureData = inputService.submit(() -> {
                try (DataReader reader = new DataReader()) {
                    return reader.read();
                }
            });

            inputService.shutdown();
        }

        RateLimiter limiter = new RateLimiterImpl(100);
        try (SequencedThreadPoolExecutor outputService = new SequencedThreadPoolExecutor(threadCount)) {

            try {
                Printer printer = new FilePrinter();

                for (Object element : futureData.get()) {
                    outputService.submit(() -> {
                        limiter.acquire();
                        printer.print(element);
                    });
                }
                outputService.process();
                outputService.waitForCompletion();
                printer.close();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
