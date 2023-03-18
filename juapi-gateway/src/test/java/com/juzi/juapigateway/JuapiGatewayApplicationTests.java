package com.juzi.juapigateway;

import com.juzi.project.rpc.RpcDemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JuapiGatewayApplicationTests {

    @DubboReference
    private RpcDemoService rpcDemoService;

    @Test
    void contextLoads() {
    }

    @Test
    void testRpc() {
        System.out.println(rpcDemoService.sayHello("world"));
    }

}
