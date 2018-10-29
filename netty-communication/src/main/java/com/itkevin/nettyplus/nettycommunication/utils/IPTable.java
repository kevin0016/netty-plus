package com.itkevin.nettyplus.nettycommunication.utils;

import java.util.regex.Pattern;

public class IPTable {
	
	/**
	 * 允许IP验证规则
	 */
	private static Pattern allowPattern;
	
	/**
	 * 禁止IP验证规则
	 */
	private static Pattern forbidPattern;
	
	static {
		String allowIP = "";
		String forbidIP = "";
		
		if (allowIP != null && !allowIP.isEmpty()) {
			allowIP = allowIP.replaceAll("\\.", "\\\\.").replaceAll(",", "|").replaceAll("\\*", "\\.\\*");
			allowPattern = Pattern.compile(allowIP);
		} else {
			allowPattern = null; // for unit test
		}
		if (forbidIP != null && !forbidIP.isEmpty()) {
			forbidIP = forbidIP.replaceAll("\\.", "\\\\.").replaceAll(",", "|").replaceAll("\\*", "\\.\\*");
			forbidPattern = Pattern.compile(forbidIP);
		} else {
			forbidPattern = null; // for unit test
		}
	}
	
	/**
	 * 检查是否是允许的IP访问
	 * @param ip - String
	 * @return boolean
	 */
	public static boolean isAllow(String ip) {
		if (ip != null && !ip.isEmpty()) {
			boolean allowMatch = true;
			boolean forbidMatch = false;

			if (allowPattern != null) {
				allowMatch = allowPattern.matcher(ip).find();
			}
			if (forbidPattern != null) {
				forbidMatch = forbidPattern.matcher(ip).find();
			}

			return (allowMatch && !forbidMatch);
		}

		return false;
	}

}
