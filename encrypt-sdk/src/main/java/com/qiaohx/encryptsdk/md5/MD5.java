package com.qiaohx.encryptsdk.md5;

import com.qiaohx.encryptsdk.common.BaseConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    private static final Logger logger = LoggerFactory.getLogger(MD5.class);

    public static String getMd5(String str, String charset) throws UnsupportedEncodingException {
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(str.getBytes(charset));
            //获得加密后的数据
            byte[] secretBytes = md.digest();
            md5 = new BigInteger(1, secretBytes).toString(16);
            for (int i = 0; i < 32 - md5.length(); i++) {
                md5 = "0" + md5;
            }
            logger.info("MD5：" + md5);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * 进行MD5摘要，使用默认UTF-8编码
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getMd5(String str) throws UnsupportedEncodingException {
        return getMd5(str, BaseConstant.DEFAULT_CHARSET);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        getMd5("123qwe");
    }
}
