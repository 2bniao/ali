package com.neo.xnol.message.disruptor.sms;

import com.lmax.disruptor.EventFactory;

public class SmsEvent {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public final static EventFactory<SmsEvent> EVENT_FACTORY = new EventFactory<SmsEvent>() {
        public SmsEvent newInstance() {
            return new SmsEvent();
        }
    };
}
