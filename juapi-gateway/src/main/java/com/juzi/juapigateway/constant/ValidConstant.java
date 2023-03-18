package com.juzi.juapigateway.constant;

/**
 * @author codejuzi
 */
public class ValidConstant {

    /**
     * 请求时间有效 5分钟 （毫秒比较）
     */
    public static final Long REQ_VALID_TIME_INTERVAL = (long) (5 * 60 * 1000);

    /**
     * nonce随机数的最大值
     */
    public static final long MAX_NONCE = 99999L;
}