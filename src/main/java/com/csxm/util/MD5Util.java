package com.csxm.util;

import java.security.MessageDigest;

/**
 * 采用MD5加密解密
 * 调用方法 MD5Util.string2MD5(s);
 * @author ds
 * @date 2011-10-13
 * @version V 1.0
 */
public class MD5Util {

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */ 
	public static String convertMD5(String inStr){

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	// 测试主函数
	public static void main(String args[]) {
//	    System.out.println(json);
//		String s = new String(json.toString());
//		System.out.println("原始：" + s);
//		String mdStr = string2MD5(s).toUpperCase();
//		System.out.println("MD5后：" + mdStr);
//		String sss = convertMD5(s);
//		System.out.println("加密的：" + sss);
//		System.out.println("解密的：" + convertMD5(sss));
	}
	
	
}
