package com.mt.disruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler2 implements EventHandler<LongEvent> {
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(Thread.currentThread().getName());
        System.out.println(longEvent.getValue() + "***222222222" + "---" + l);
    }
}