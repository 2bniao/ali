package com.mt.service;

import com.mt.vo.SmsSendlogHis;

public interface SmsServiceI {
    SmsSendlogHis selectByPrimaryKey(String mobile);

}
