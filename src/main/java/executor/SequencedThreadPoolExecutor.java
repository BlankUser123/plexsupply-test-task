package executor;

import java.util.*;
import java.util.concurrent.*;

public class SequencedThreadPoolExecutor implements AutoCloseable {

    private final int threadCount;
    private final List<Thread> threads = new ArrayList<>();
    private final BlockingQueue<Runnable> commands = new LinkedBlockingQueue<>();
    private boolean isShutdown = false;

    public SequencedThreadPoolExecutor(int threadCount) {
        this.threadCount = threadCount;
    }

    public synchronized void submit(Runnable command) {
        if (isShutdown) {
            throw new IllegalStateException("Executor has been shut down");
        }
        commands.offer(command);
        notifyAll();
    }

    public synchronized void process() {
        for (int i = threads.size(); i < threadCount; i++) {
            Thread thread = new Thread(this::processTasks);
            threads.add(thread);
            thread.start();
        }
    }

    private synchronized void processTasks() {
        while (true) {
            Runnable command;
            synchronized (this) {
                while (commands.isEmpty() && !isShutdown) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (isShutdown && commands.isEmpty()) {
                    break;
                }

                command = commands.poll();
            }

            if (command != null) {
                command.run();
            }
        }
    }

    public synchronized void shutdown() {
        isShutdown = true;
        notifyAll();
    }

    public synchronized void waitForCompletion() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws Exception {
        shutdown();
        waitForCompletion();
        threads.clear();
    }
}
