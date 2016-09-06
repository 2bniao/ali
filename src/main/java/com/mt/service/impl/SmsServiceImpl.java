package com.mt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.mapper.SmsSendlogHisDAO;
import com.mt.service.SmsServiceI;
import com.mt.vo.SmsSendlogHis;

@Service("smsService")
public class SmsServiceImpl implements SmsServiceI {

    @Autowired
    private SmsSendlogHisDAO smsSendlogHisDAO;

    @Override
    public SmsSendlogHis selectByPrimaryKey(String mobile) {
        return smsSendlogHisDAO.selectByPrimaryKey(mobile);
    }

}
