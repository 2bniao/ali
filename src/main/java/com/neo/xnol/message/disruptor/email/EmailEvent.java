package com.neo.xnol.message.disruptor.email;

import com.lmax.disruptor.EventFactory;

public class EmailEvent {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public final static EventFactory<EmailSendlogHis> EVENT_FACTORY = new EventFactory<EmailSendlogHis>() {
        public EmailSendlogHis newInstance() {
            return new EmailSendlogHis();
        }
    };
}
