package pers.platform.common.utils.cryptography;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 加密工具
 * 
 * @author Administrator
 *
 */
public class CryptographyUtil {
    /**
     * Md5加盐加密
     * 
     * @param str
     * @param salt
     * @return
     */
    public static String md5(String str, String salt) {
        return new Md5Hash(str, salt).toString();
    }

     public static void main(String[] args) {
//     System.out.println(CryptographyUtil.md5("admin",
//             "eecfd13489fdd1c786e747168bc3d7ef")); }
             String algorithmName = "md5";
             String username = "admin";
             String password = "admin";
             int hashIterations = 3;
             SimpleHash hash = new SimpleHash(algorithmName, password,
                     "d9cb297199c5da0cf49c8017aeb1aa5bd7ef", hashIterations);
             String encodedPassword = hash.toHex();
             System.out.println(encodedPassword);
        }
     }
