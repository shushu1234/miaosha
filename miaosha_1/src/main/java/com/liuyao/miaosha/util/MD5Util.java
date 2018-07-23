package com.liuyao.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author liuyao
 * @date 2018/07/23
 */
public class MD5Util {
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1q2w3e4r";

    /**
     * 第一次md5
     *
     * @param inputPass 用户输入的密码
     * @return
     */
    public static String inputPassFromPass(String inputPass) {
        String str = "" + salt.charAt(5) + salt.charAt(1) + inputPass + salt.charAt(4) + salt.charAt(2);
        return md5(str);
    }

    /**
     * 第二次md5 随机salt
     *
     * @param inputPass
     * @param salt
     * @return
     */
    public static String fromPassToDBPass(String inputPass, String salt) {
        String str = "" + salt.charAt(5) + salt.charAt(1) + inputPass + salt.charAt(4) + salt.charAt(2);
        return md5(str);
    }

    public static String inputPassToDBPass(String input, String saltDB) {
        String fromPass = inputPassFromPass(input);
        String dbPass = fromPassToDBPass(fromPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
//        System.out.println(inputPassFromPass("123456")); //c8a400d7d0f68742b5633dc5a493372e
//        System.out.println(fromPassToDBPass("12345", "11w2e3r4r"));
        System.out.println(inputPassToDBPass("123456", "1q2w3r4r"));
    }
}
