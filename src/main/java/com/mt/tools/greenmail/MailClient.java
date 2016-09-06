package com.mt.tools.greenmail;

import com.alibaba.fastjson.JSON;
import java.util.Map;
import java.util.logging.Logger;

public class MailClient {
	private static final Logger logger = Logger.getLogger(MailClient.class.getName());
	private String salt;
	private String remoteUserId;
	private int readTimeOut=30000;
	private int connectionTimeOut=30000;

	public MailClient(String salt, String remoteUserId) {
		this.salt = salt;
		this.remoteUserId = remoteUserId;
	}
	
	public MailClient(String salt, String remoteUserId,int readTimeOut,int connectionTimeOut) {
		this.salt = salt;
		this.remoteUserId = remoteUserId;
		this.readTimeOut = readTimeOut;
		this.connectionTimeOut = connectionTimeOut;
	}

	public SdkResp sendRequest(String apiUrl, Map<String, String> parameters) {
		return sendRequest(apiUrl, parameters, null);
	}

	public SdkResp sendRequest(String apiUrl, Map<String, String> parameters, Map<String, String> headers) {
		SdkResp resp = new SdkResp();
		try {
			ApiSender sender = new ApiSender(this.salt, this.remoteUserId,readTimeOut,connectionTimeOut);
			sender.addParameters(parameters);
			sender.addHeaders(headers);
			String respText = sender.sendHttpRequest(apiUrl);

			logger.info("return result：" + respText);

			resp = (SdkResp) JSON.parseObject(respText, SdkResp.class);
		} catch (Exception e) {
			logger.severe(e.getMessage());
			resp.setSuccess(false);
			resp.setMessage(e.getMessage());
		}

		return resp;
	}
}