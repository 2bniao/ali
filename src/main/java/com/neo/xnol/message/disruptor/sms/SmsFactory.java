package com.neo.xnol.message.disruptor.sms;

import com.lmax.disruptor.EventFactory;

public class SmsFactory implements EventFactory {

    public Object newInstance() {
        return new SmsEvent();
    }

}