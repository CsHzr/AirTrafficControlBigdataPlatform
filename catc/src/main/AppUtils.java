package main;

public class AppUtils {
	/**
	 * @see 将byte[]数组转换为String字符串
	 * @author Herman.Xiong
	 * @date 2014年5月5日 17:15:42
	 * @param data byte数组
	 * @return String 转换后的字符串
	 */
	public static String byteToArray(byte[]data){
		String result="";
		for (int i = 0; i < data.length; i++) {
			result+=Integer.toHexString((data[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3);
		}
		return result;
	}

}
