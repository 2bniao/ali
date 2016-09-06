package com.neo.xnol.message.disruptor.email;

import java.util.Date;

import ooh.bravo.core.model.BaseObject;

public class EmailSendlogHis extends BaseObject {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String source;

    private Integer msgEventId;

    private String email;

    private String title;

    private String content;

    private Integer sendStatus;

    private Date sendTime;

    private Date createTime;

    public EmailSendlogHis() {
        super();
    }

    public EmailSendlogHis(String source, Integer msgEventId, String email, String title, String content,
            Integer sendStatus, Date sendTime, Date createTime) {
        super();
        this.source = source;
        this.msgEventId = msgEventId;
        this.email = email;
        this.title = title;
        this.content = content;
        this.sendStatus = sendStatus;
        this.sendTime = sendTime;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getMsgEventId() {
        return msgEventId;
    }

    public void setMsgEventId(Integer msgEventId) {
        this.msgEventId = msgEventId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}