package com.neo.xnol.message.disruptor.email;

import com.lmax.disruptor.EventFactory;

public class EmailFactory implements EventFactory {

    public Object newInstance() {
        return new EmailEvent();
    }

}