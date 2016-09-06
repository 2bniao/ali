package com.mt.xuanwu;

import java.util.ArrayList;
import java.util.UUID;

import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;
import com.esms.common.entity.MTPack.MsgType;
import com.esms.common.entity.MTPack.SendType;

public class Test {
    public static void main(String[] args) {
        Account ac = new Account("xnzx@szxnzx", "I910wWSz");//
        PostMsg pm = new PostMsg();
        MTPack pack = new MTPack();
        pack.setBatchID(UUID.randomUUID());
        pack.setBatchName("短信测试批次");

        // 以下为mos地址
        pm.getCmHost().setHost("211.147.239.62", 9080);// 设置网关的IP和port，用于发送信息，建议可配
        pm.getWsHost().setHost("211.147.239.62", 9070);// 设置网关的IP和port，用于获取账号信息、上行、状态报告等等，建议可配

        pack.setMsgType(MsgType.SMS);
        pack.setBizType(0);
        pack.setDistinctFlag(false);
        ArrayList<MessageData> msgs = new ArrayList<MessageData>();
        pack.setSendType(SendType.MASS);
        /** 单发，一号码一内容 */
        msgs.add(new MessageData("18688736814", "短信单发测试"));
        pack.setMsgs(msgs);
        // pack.setSendType(SendType.GROUP);

        // pack.setSendType(MTPack.SendType.MASS);
        // String content = "短信群发测试";
        // msgs.add(new MessageData("18688736814", content));
        // msgs.add(new MessageData("18688736814", content));
        // pack.setMsgs(msgs);

        GsmsResponse resp;
        try {
            resp = pm.post(ac, pack);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(resp);
    }

}
