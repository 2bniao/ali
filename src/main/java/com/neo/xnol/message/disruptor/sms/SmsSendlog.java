package com.neo.xnol.message.disruptor.sms;

import java.util.Date;

import ooh.bravo.core.model.BaseObject;

public class SmsSendlog extends BaseObject {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String source;

    private Integer msgEventId;

    private Integer priority;

    private String mobile;

    private String content;

    private Date createTime;

    private Long smsSendlogHisId;

    private Long smsId;

    public SmsSendlog() {
        super();
    }

    public SmsSendlog(String source, Integer msgEventId, Integer priority, String mobile, String content,
            Date createTime) {
        super();
        this.source = source;
        this.msgEventId = msgEventId;
        this.priority = priority;
        this.mobile = mobile;
        this.content = content;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getSmsSendlogHisId() {
        return smsSendlogHisId;
    }

    public void setSmsSendlogHisId(Long smsSendlogHisId) {
        this.smsSendlogHisId = smsSendlogHisId;
    }

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    @Override
    public String toString() {
        return "SmsSendlog [id=" + id + ", source=" + source + ", msgEventId=" + msgEventId + ", priority=" + priority
                + ", mobile=" + mobile + ", content=" + content + ", createTime=" + createTime + "]";
    }

}