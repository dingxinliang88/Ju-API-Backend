package com.juzi.juziinterface.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.juzi.juziapiclientsdk.model.MockUser;
import com.juzi.juziapiclientsdk.util.SignUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

import static com.juzi.juziinterface.constant.ValidConstant.REQ_VALID_TIME_INTERVAL;

/**
 * 模拟用户请求接口类
 *
 * @author codejuzi
 */
@RestController
@RequestMapping("/name")
public class MockUserController {
    @GetMapping("/{name}")
    public String getNameByGet(@PathVariable(value = "name") String name) {
        return "--[GET]-- Your Name :" + name;
    }

    @PostMapping()
    public String getNameByPost(@RequestParam String name) {
        return "--[POST]-- Your Name :" + name;
    }

    @PostMapping("/user")
    public String getNameByPostWithJson(@RequestBody MockUser mockUser, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        // 防止中文乱码
        String body = request.getHeader("body");
        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        boolean hasBlank = StrUtil.hasBlank(accessKey, body, sign, nonce, timestamp);
        // 判空
        if(hasBlank) {
            return "无权限";
        }

        // TODO: 2023/3/10 改为去数据库查询secretKey
        String secretKey = "juzi";
        String generateSign = SignUtil.generateSign(body, secretKey);
        if(!StrUtil.equals(sign, generateSign)) {
            return "无权限";
        }
        // TODO: 2023/3/10 判断随机数 nonce
        // 时间戳是否是数字
        if(!NumberUtil.isNumber(timestamp)) {
            return "无权限";
        }
        // 五分钟内请求有效
        if(System.currentTimeMillis() - Long.parseLong(timestamp) > REQ_VALID_TIME_INTERVAL) {
            return "无权限";
        }
        return "--[POST && JSON]-- Your Name :" + mockUser.getName();
    }
}
