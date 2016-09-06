package com.mt.disruptor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;

public class LongEventMain {
    public static void main(String[] args) throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();
        // The factory for the event
        LongEventFactory factory = new LongEventFactory();
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;
        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);

        // Connect the handler
        // disruptor.handleEventsWith(new LongEventHandler2(), new LongEventHandler2());
        disruptor.handleEventsWithWorkerPool(new Handler3(), new Handler3());
        // Start the Disruptor, starts all threads running
        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        for (long i = 0; i < 50000; i++) {
            producer.onData(i);
        }
        disruptor.shutdown();
    }

    static class Handler3 implements WorkHandler<LongEvent> {

        @Override
        public void onEvent(LongEvent event) throws Exception {
            System.out.println("workHandler===" + event.getValue() + Thread.currentThread().getName());

        }

    }
}
