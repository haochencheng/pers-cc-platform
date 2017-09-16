package pers.platfrom.common.utils.cryptography;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Date;

/**
 * DES加密介绍 DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
 * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
 * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现 。
 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class DES {
	public DES() {
	}

	/**
	 * 加密
	 * @param datasource
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 */
	public static byte[] encrypt(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}
	/**
	 * 根据参数生成cookies，这里没有太复杂，以后可以扩展，通过修改cookiesEncoder
	 * @param userName
	 * @param password
	 * @param remoteAddress
	 * @param expriDate
	 * @param ticketId
	 * @param salt
	 * @return
	 * Cookievalue = base64(des(md5(Username) + “:” + md5(password) + “:” + ip + “:” + expire_date + “:” + ticketid, sitepwd))
	 */
	
	public static String encodeCookies(String userName,String password,String remoteAddress,Date expriDate,String ticketId,String salt) {
		String strDes = userName+":"+password+":"+remoteAddress+":"+expriDate.getTime()+":"+ticketId+":"+salt;
		strDes = MD5.convertMD5(strDes);
		String cookies = Base64.encodeBase64String(strDes.getBytes());
		return cookies;
	}
	/**
	 * cookies解密
	 * @param cookies
	 * @param salt
	 * @return
	 * @throws Exception
	 */
	public static  String decodeCookies(String cookies,String salt)throws Exception{
		String decodedCookies = new String(Base64.decodeBase64(cookies));
		decodedCookies = MD5.convertMD5(decodedCookies);
		return decodedCookies;
	}
	public static String[] decodeCookiesArray(String cookies,String salt)throws Exception{
		return decodeCookies(cookies,salt).split(":");
	}
}