package rate_limiter;

import java.util.concurrent.*;

public class RateLimiterImpl implements RateLimiter {

    private final int maxRequestsPerSecond;
    private int permits;
    private long lastResetTime = System.currentTimeMillis();
    private final Object lock = new Object();

    public RateLimiterImpl(int maxRequestsPerSecond) {
        this.maxRequestsPerSecond = maxRequestsPerSecond;
        this.permits = maxRequestsPerSecond;
    }

    @Override
    public void acquire() {
        synchronized (lock) {
            while (true) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastResetTime >= 1000) {
                    permits = maxRequestsPerSecond;
                    lastResetTime = currentTime;
                }

                if (permits > 0) {
                    permits--;
                    return;
                }

                try {
                    long waitTime = 1000 - (currentTime - lastResetTime);
                    if (waitTime > 0) {
                        lock.wait(waitTime);
                    } else {
                        lock.wait(10); // fallback
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
