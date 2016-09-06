package com.neo.xnol.message.disruptor.email;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

@Component
public class EmailEventProducer {
    private Disruptor<EmailEvent> disruptor;

    @Autowired
    private EmailConsumeEventHandler emailConsumeEventHandler;

    @PostConstruct
    private void init() {
        int bufferSize = 512;
        EmailFactory factory = new EmailFactory();
        /*
         * disruptor = new Disruptor<EmailEvent>(factory, bufferSize, executor, ProducerType.SINGLE, new
         * LiteBlockingWaitStrategy());
         */

        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        disruptor = new Disruptor<EmailEvent>(factory, bufferSize, threadFactory);
        disruptor.handleEventsWith(emailConsumeEventHandler);
        disruptor.start();
    }

    /** 
     * onData用来发布事件，每调用一次就发布一次事件事件 
     * 它的参数会通过事件传递给消费者 
     * 
     * @param bb 
     */
    public void onData(EmailSendlogHis emailSendlogHis) {
        // 可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
        RingBuffer<EmailEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();
        try {
            // 用上面的索引取出一个空的事件用于填充
            EmailEvent event = ringBuffer.get(sequence);// for the sequence
            event.setContent("email....");
        } finally {
            // 发布事件
            ringBuffer.publish(sequence);
        }
    }
}
