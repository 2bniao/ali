package com.mchuan.mcloud.client.test.ext;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class MessageUtilsJson {
	
	private MessageUtilsJson(){};
	
    private static final  String sendUrl="http://112.74.139.4:8002/sms3_api/jsonapi/jsonrpc2.jsp";
	
	public static String sendMsg(String userid,String password,String phone,String content) throws HttpException, IOException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("id","1");
		jsonObject.accumulate("method","send");
		
		JSONObject jsonParams = new JSONObject();
		jsonParams.accumulate("userid", userid);
		jsonParams.accumulate("password", password);
		
		JSONObject jsonSubmits = new JSONObject();
		jsonSubmits.accumulate("content", content);
		jsonSubmits.accumulate("phone", phone);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(jsonSubmits);
		
		jsonParams.accumulate("submit",jsonArray);
		jsonObject.accumulate("params", jsonParams);
		
		System.out.println(jsonObject.toString());
		String result = submitUrl(jsonObject, sendUrl);
		return result;
		
	}
	
	// 获取短信状态
	public static String getMsgStatus(String userid,String password) throws HttpException, IOException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("id","1");
		jsonObject.accumulate("method","report");
		
		JSONObject jsonParams = new JSONObject();
		jsonParams.accumulate("userid", userid);
		jsonParams.accumulate("password", password);
		jsonObject.accumulate("params", jsonParams);
		System.out.println(jsonObject.toString());
		String result = submitUrl(jsonObject, sendUrl);
		return result;
	}
	
	public static String getMsgUp(String userid,String password) throws HttpException, IOException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("id","1");
		jsonObject.accumulate("method","upmsg");
		
		JSONObject jsonParams = new JSONObject();
		jsonParams.accumulate("userid", userid);
		jsonParams.accumulate("password", password);
		jsonObject.accumulate("params", jsonParams);
		System.out.println(jsonObject.toString());
		String result = submitUrl(jsonObject, sendUrl);
		return result;
	}
	
	public static String submitUrl(JSONObject jsonobject,String url) throws IOException, HttpException {
		HttpClient httpClient = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(300000);
		
		RequestEntity requestEntity=new StringRequestEntity(jsonobject.toString());
		method.setRequestEntity(requestEntity);
		httpClient.executeMethod(method);
		String result = method.getResponseBodyAsString();
		return result;
	}
	
	
}
