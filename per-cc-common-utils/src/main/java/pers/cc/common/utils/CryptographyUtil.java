package pers.cc.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具
 * 
 * @author Administrator
 *
 */
public class CryptographyUtil {
	/**
	 * Md5����
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	public static void main(String[] args) {
		System.out.println(CryptographyUtil.md5("123456", "cc"));
	}
}
