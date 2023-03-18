package com.juzi.juziinterface.controller;

import com.juzi.juziapiclientsdk.model.MockUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 模拟用户请求接口类
 *
 * @author codejuzi
 */
@Slf4j
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
        return "--[POST && JSON]-- Your Name :" + mockUser.getName();
    }
}
