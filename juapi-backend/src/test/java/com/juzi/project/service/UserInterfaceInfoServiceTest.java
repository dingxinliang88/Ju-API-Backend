package com.juzi.project.service;

import com.juzi.project.service.impl.inner.InnerUserInterfaceInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 用户服务测试
 *
 * @author juzi
 */
@SpringBootTest
class UserInterfaceInfoServiceTest {

    @Resource
    private InnerUserInterfaceInfoServiceImpl innerUserInterfaceInfoService;

    @Test
    void doContext() {
        boolean b = innerUserInterfaceInfoService.invokeInterfaceCount(1L, 1L);
        System.out.println(b);
    }

}