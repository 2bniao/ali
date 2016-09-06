package com.mt.tools.greenmail;

public class SdkStringUtils {
	public static boolean isBlank(String s) {
		if (s == null) {
			return true;
		}

		if ("".equals(s)) {
			return true;
		}

		return false;
	}

	public static boolean isNotBlank(String s) {
		return !isBlank(s);
	}

	public static String arrayToString(String[] strs, String regex) {
		if ((strs == null) || (strs.length == 0)) {
			return "";
		}

		String str = "";

		String[] arrayOfString = strs;
		int j = strs.length;
		for (int i = 0; i < j; i++) {
			String address = arrayOfString[i];
			str = str + regex + address;
		}

		str = str.replaceFirst(regex, "");

		return str;
	}
}