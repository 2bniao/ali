package com.mt.vo;

import java.util.Date;

import ooh.bravo.core.model.BaseObject;

public class SmsSendlogHis extends BaseObject {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long smsId;

    private String source;

    private Integer msgEventId;

    private Integer priority;

    private String mobile;

    private String content;

    private Integer sendStatus;

    private Date sendTime;

    private Date createTime;

    public SmsSendlogHis() {
        super();
    }

    public SmsSendlogHis(Long smsId, String source, Integer msgEventId, Integer priority, String mobile,
            String content, Integer sendStatus, Date sendTime, Date createTime) {
        super();
        this.smsId = smsId;
        this.source = source;
        this.msgEventId = msgEventId;
        this.priority = priority;
        this.mobile = mobile;
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

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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