package com.juzi.juziapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.juzi.juziapiclientsdk.model.MockUser;
import com.juzi.juziapiclientsdk.util.SignUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * API调用工具类
 *
 * @author codejuzi
 */
public class JuziApiClient {

    private final String accessKey;

    private final String secretKey;

    public JuziApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        return HttpUtil.get("http://localhost:8123/api/name/" + name);
    }

    public String getNameByPost(String name) {
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("name", name);
        return HttpUtil.post("http://localhost:8123/api/name", paramMap);
    }

    public String getNameByPostWithJson(MockUser mockUser) {
        String mockUserJson = JSONUtil.toJsonStr(mockUser);
        HttpResponse response = HttpRequest.post("http://localhost:8123/api/name/user")
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

    private Map<String, String> getHeaders(String body) {
        Map<String, String> header = new HashMap<>();
        header.put("accessKey", accessKey);
        header.put("sign", SignUtil.generateSign(body, secretKey));
        // 防止中文乱码
        header.put("body", body);
        header.put("nonce", RandomUtil.randomNumbers(5));
        header.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return header;
    }
}
