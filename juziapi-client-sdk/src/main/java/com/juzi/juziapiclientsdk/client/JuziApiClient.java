package com.juzi.juziapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.juzi.juziapiclientsdk.model.MockUser;
import com.juzi.juziapiclientsdk.util.SignUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * API调用工具类
 *
 * @author codejuzi
 */
public class JuziApiClient {

    private static final String GATEWAY_HOST = "http://localhost:8090";

    private final String accessKey;

    private final String secretKey;

    public JuziApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        return HttpUtil.get(GATEWAY_HOST + "/api/name/" + name);
    }

    public String getNameByPost(String name) {
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("name", name);
        return HttpUtil.post(GATEWAY_HOST + "/api/name", paramMap);
    }

    public String getNameByPostWithJson(MockUser mockUser) throws UnsupportedEncodingException {
        String mockUserJson = JSONUtil.toJsonStr(mockUser);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                .header("Content-Type", "application/json; charset=utf-8")
                .addHeaders(getHeaders(mockUserJson))
                .body(mockUserJson, "application/json; charset=utf-8")
                .execute();
        System.out.println("response = " + response);
        int status = response.getStatus();
        System.out.println("status = " + status);
        if (response.isOk()) {
            return response.body();
        }
        return "fail";
    }

    private Map<String, String> getHeaders(String body) throws UnsupportedEncodingException {
        Map<String, String> header = new HashMap<>(16);
        header.put("accessKey", accessKey);
        header.put("sign", SignUtil.generateSign(body, secretKey));
        // 防止中文乱码
        header.put("body", URLEncoder.encode(body, "utf-8"));
        header.put("nonce", RandomUtil.randomNumbers(5));
        header.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return header;
    }
}
