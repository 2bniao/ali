package com.mt.tools.greenmail;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

public class ApiSender {
	private static final Logger logger = Logger.getLogger(ApiSender.class.getName());
	private static final String charcoding = "utf-8";
	private   int readTimeOut = 15000;
	private   int connectionTimeOut = 30000;
	private static final String requestMethod = "POST";
	private Map<String, String> parameters = new HashMap();

	private Map<String, String> headers = new HashMap();
	private String remoteUserId;
	private String salt;

	public ApiSender(String salt, String remoteUserId,int readTimeOut,int connectionTimeOut) {
		this.salt = salt;
		this.remoteUserId = remoteUserId;
		this.readTimeOut =readTimeOut;
		this.connectionTimeOut =connectionTimeOut;
	}

	public Map<String, String> getParameters() {
		return this.parameters;
	}

	public void addParameters(Map<String, String> parameters) {
		if (parameters == null) {
			return;
		}

		Set<Entry<String, String>> set = parameters.entrySet();

		for (Map.Entry entry : set) {
			if (!this.parameters.containsKey(entry.getKey())) {
				this.parameters.put((String) entry.getKey(), (String) entry.getValue());
			}
		}

		this.parameters = parameters;
	}

	public Map<String, String> getHeaders() {
		return this.headers;
	}

	public void addHeaders(Map<String, String> headers) {
		if (headers == null) {
			return;
		}
		this.headers.putAll(headers);
	}

	public void addHeader(String name, String value) {
		this.headers.put(name, value);
	}

	public void addParameter(String name, String value) {
		this.parameters.put(name, value);
	}

	public String sendHttpRequest(String url) throws Exception {
		if (!url.startsWith("http://")) {
			url = "http://" + url;
		}

		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setDefaultUseCaches(false);
		conn.setConnectTimeout(readTimeOut);
		conn.setReadTimeout(connectionTimeOut);
		conn.setRequestMethod("POST");

		if ((this.headers != null) && (!this.headers.isEmpty())) {
			Set<Entry<String, String>> set = this.headers.entrySet();
			for (Map.Entry entry : set) {
				conn.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
			}

		}

		conn.setDoOutput(true);
		conn.setDoInput(true);

		String outputText = composeHttpParameters();

		logger.info(outputText);

		if ((outputText != null) && (!"".equals(outputText))) {
			OutputStream os = conn.getOutputStream();
			os.write(outputText.getBytes("utf-8"));
			os.flush();
		}

		conn.connect();

		String responseText = "";
		if (conn.getResponseCode() == 200) {
			InputStream is = conn.getInputStream();
			byte[] buffer = new byte[8196];
			int count = 0;
			while ((count = is.read(buffer)) != -1)
				responseText = responseText + new String(buffer, 0, count, "utf-8");
		} else {
			responseText = "{\"SUCCESS\":\"false\",\"MESSAGE\":\"HTTPCode=" + conn.getResponseCode()
					+ "\",\"RESULT\":\"\"}";
		}
		conn.disconnect();

		return responseText;
	}

	private String composeHttpParameters() throws Exception {
		String outputText = "";

		addAuthParameters();

		if ((this.parameters == null) || (this.parameters.isEmpty())) {
			return outputText;
		}

		Set<Entry<String, String>> set = this.parameters.entrySet();

		for (Map.Entry entry : set) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();

			if ((SdkStringUtils.isNotBlank(key)) && (SdkStringUtils.isNotBlank(value))) {
				outputText = outputText + "&" + key + "=" + URLEncoder.encode(value, "utf-8");
			}

		}

		if (SdkStringUtils.isNotBlank(outputText)) {
			outputText = outputText.substring(1, outputText.length());
		}

		return outputText;
	}

	private void addAuthParameters() throws Exception {
		if (this.parameters == null) {
			return;
		}

		this.parameters.put("remote_user_id", this.remoteUserId);
		this.parameters.put("remote_time", String.valueOf(System.currentTimeMillis()));
		this.parameters.put("salt", this.salt);

		String md5 = getRemoteVerifyCode(this.parameters);

		this.parameters.put("remote_verify_code", md5);

		this.parameters.remove("salt");
	}

	public static String getRemoteVerifyCode(Map<String, String> parameters) throws Exception {
		List<String> list = sortKeys(parameters);

		StringBuilder builder = new StringBuilder();
		for (String key : list) {
			builder.append((String) parameters.get(key));
		}
		String md5 = md5Encode(builder.toString());
		return md5;
	}

	private static List<String> sortKeys(Map<String, String> parameters) {
		Set keys = parameters.keySet();
		List list = new LinkedList();
		list.addAll(keys);
		Collections.sort(list);
		return list;
	}

	private static String md5Encode(String str) throws Exception {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw e;
		} catch (UnsupportedEncodingException e) {
			throw e;
		}
		byte[] byteArray = messageDigest.digest();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				builder.append("0");
				builder.append(Integer.toHexString(0xFF & byteArray[i]));
			} else {
				builder.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return builder.toString();
	}
}