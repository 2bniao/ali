package com.neo.xnol.message.disruptor.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.EventHandler;

@Component
public class SmsConsumeEventHandler implements EventHandler<SmsEvent> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onEvent(SmsEvent smsEvent, long arg1, boolean arg2) throws Exception {
    }

}
