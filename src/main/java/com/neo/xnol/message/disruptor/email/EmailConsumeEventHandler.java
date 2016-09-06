package com.neo.xnol.message.disruptor.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.EventHandler;

@Component
public class EmailConsumeEventHandler implements EventHandler<EmailEvent> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmailEventProducer emailEventProducer;

    public void onEvent(EmailEvent event, long sequence, boolean endOfBatch) throws Exception {
    }

}
