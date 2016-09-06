package com.mt.disruptor;

import com.lmax.disruptor.WorkHandler;

public class LongEventHandler implements WorkHandler<LongEvent> {
    /*
     * public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
     * System.out.println(longEvent.getValue() + "***1111111111"); }
     */

    @Override
    public void onEvent(LongEvent event) throws Exception {
        System.out.println(event.getValue() + "***1111111111");
    }
}