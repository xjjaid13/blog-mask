package com.util;

public class UserDES {

	private UserDES() {

	}

	private static String loginIDCrypt = "1";
	private static String Key1 = "FoGoToCo";// 加密用的密鈅
	private static String Key2 = "Lwcfoxhunter";// 加密用的密鈅
	private static String Key3 = "Sowwa";// 加密用的密鈅

	/**
	 * 加密
	 * 
	 * @param key
	 *            需要加密的内容
	 * @return
	 */
	public static String enCipher(String content) {
		if ("1".equals(loginIDCrypt)) {
			return DES.strEnc(content, Key1, Key2, Key3);
		} else {
			return content;
		}
	}

	/**
	 * 解密
	 * 
	 * @param key
	 *            待解密内容
	 * @return
	 */
	public static String unCipher(String content) {
		if ("1".equals(loginIDCrypt)) {
			return new String(DES.strDec(content, Key1, Key2, Key3));
		} else {
			return content;
		}
	}

}
