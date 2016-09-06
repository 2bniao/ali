package com.mt.test.thread;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T1 {
    public static void main(String[] args) throws InterruptedException {
        Executor executor = new ThreadPoolExecutor(3, 4, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 50; i++) {
            executor.execute(new Task("OWEN" + i));
        }
        Map map = Thread.getAllStackTraces(); // 也可以map<Thread, StackTraceElement[]>
        System.out.println("当前线程数：" + map.size());
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Thread t = (Thread) it.next(); //
            System.out.println(t.getName() + "===");
        }
        System.out.println("**********" + Thread.activeCount());
        Thread.sleep(70000);
        System.out.println(Thread.activeCount());
    }

}
