package com.mt.test.puling;

public class ShanlinTask implements Runnable {

    public void run() {
        System.out.println("task---run");
        System.out.println("task---run===" + Thread.currentThread().getName());

        String url = "http://203.156.234.123/sendSms.asp";
        // String url = "http://203.156.234.123/getReport.asp";
        String account = "szxn";
        String password = "Abcd1234";
        String phones = "18688736814";
        System.out.println("task---runpp");
        // String content = URLEncoder.encode("【小牛在线】尊敬的客户：您于2016-01-20
        // 01:27:35购买了CreateByLr26865-10,购买金额为：100元。退订回N",
        // "gb2312");
        System.out.println("task---run,,,,,");
        String channel = "1";
        String request = "account=" + account + "&password=" + password + "&phones=" + phones + "&content=" + "111"
                + "&channel=" + channel;
        System.out.println("task---run;;;;");
        String ret1 = "";
        // String request = "account=" + account + "&password=" + password + "&Time=20160302";
        ret1 = HttpRequest.sendGet(url, request);
        System.out.println(ret1 + "===" + Thread.currentThread().getName());
    }

}
