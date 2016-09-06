package com.mt.test.puling;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PulinTest {
    public static void main(String[] args) {
        // Executor executor = new ThreadPoolExecutor(3, 4, 3, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(512),
        // new NamedThreadFactory("Message Center", true));

        Executor executor = new ThreadPoolExecutor(2, 2 * 2, 3, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(512),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 5; i++) {
            executor.execute(new ShanlinTask());
        }
    }
}
