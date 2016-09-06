package com.mt.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkerPool;

public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
        LongEventHandler[] handlers = new LongEventHandler[2];
        for (int i = 0; i < 2; i++) {
            handlers[i] = new LongEventHandler();
        }
        WorkerPool<LongEvent> workerPool2 = new WorkerPool<LongEvent>(ringBuffer, ringBuffer.newBarrier(),
                new DisException(), handlers);
        ringBuffer.addGatingSequences(workerPool2.getWorkerSequences());
    }

    /** 
     * onData用来发布事件，每调用一次就发布一次事件事件 
     * 它的参数会通过事件传递给消费者 
     * 
     * @param bb 
     */
    public void onData(long l) {
        // 可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
        long sequence = ringBuffer.next();

        try {
            // 用上面的索引取出一个空的事件用于填充
            LongEvent event = ringBuffer.get(sequence);// for the sequence
            event.setValue(l);
        } finally {
            // 发布事件
            ringBuffer.publish(sequence);
        }
    }
}
