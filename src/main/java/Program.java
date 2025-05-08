import console_reader.ThreadCountConsoleReader;
import executor.SequencedThreadPoolExecutor;
import file_reader.DataReader;
import printer.ConsolePrinter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;

public class Program {

    public static void main(String[] args) {

        int threadCount = new ThreadCountConsoleReader().read();

        try (ExecutorService inputService = Executors.newFixedThreadPool(1);
             SequencedThreadPoolExecutor outputService = new SequencedThreadPoolExecutor(threadCount)) {

            Future<Object[]> futureData = inputService.submit(() -> {
                try (DataReader reader = new DataReader()) {
                    return reader.read();
                }
            });

            try {
                for (Object element : futureData.get()) {
                    outputService.submit(() -> {
                        new ConsolePrinter().print(element);

                        try {
                            Thread.sleep(Duration.of(1, ChronoUnit.SECONDS));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }

                outputService.process();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }

            inputService.shutdown();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
