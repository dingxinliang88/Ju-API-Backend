package com.juzi.juziinterface.client;

import com.juzi.juziapiclientsdk.client.JuziApiClient;
import com.juzi.juziapiclientsdk.model.MockUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * @author codejuzi
 */
@SpringBootTest
class JuziApiClientTest {

    @Resource
    private JuziApiClient juziApiClient;

    @Test
    void testApiClient() throws UnsupportedEncodingException {
        MockUser mockUser = new MockUser();
        mockUser.setName("橘子");
        String res3 = juziApiClient.getNameByPostWithJson(mockUser);
        System.out.println("res3 = " + res3);
    }

}