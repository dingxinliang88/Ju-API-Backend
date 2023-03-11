package com.juzi.juziapiclientsdk.util;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * 签名生成工具类
 *
 * @author codejuzi
 */
public class SignUtil {

    public static String generateSign(String body, String secretKey) {
        String content = body + "." + secretKey;
        return DigestUtil.md5Hex(content);
    }
}
