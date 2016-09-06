package com.neo.xnol.message.disruptor.sms;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

@Component
public class SmsEventProducer {
    private Disruptor<SmsEvent> disruptor;

    @Autowired
    private SmsConsumeEventHandler smsConsumeEventHandler;

    @PostConstruct
    private void init() {
        int bufferSize = 512;
        SmsFactory factory = new SmsFactory();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        disruptor = new Disruptor<SmsEvent>(factory, bufferSize, threadFactory);
        disruptor.handleEventsWith(smsConsumeEventHandler);
        disruptor.start();
    }

    /** 
     * onData用来发布事件，每调用一次就发布一次事件事件 
     * 它的参数会通过事件传递给消费者 
     * 
     * @param bb 
     */
    public void onData(SmsSendlog smsSendlog) {
        // 可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
        RingBuffer<SmsEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();
        try {
            // 用上面的索引取出一个空的事件用于填充
            SmsEvent event = ringBuffer.get(sequence);// for the sequence
            event.setContent("sms...");
        } finally {
            // 发布事件
            ringBuffer.publish(sequence);
        }
    }
}
