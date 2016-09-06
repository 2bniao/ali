package com.mt.tools.greenmail;

public class SdkResp {
	private boolean success;
	private String message;
	private String result;

	public SdkResp() {
	}

	public SdkResp(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String toString() {
		return "{\"SUCCESS\":\"" + this.success + "\",\"MESSAGE\":" + this.message + "\",\"RESULT\":\"" + this.result
				+ "\"}";
	}
}