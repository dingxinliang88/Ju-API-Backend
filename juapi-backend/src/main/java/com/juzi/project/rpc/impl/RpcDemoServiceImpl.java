package com.juzi.project.rpc.impl;

import com.juzi.project.rpc.RpcDemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author codejuzi
 * @CreateTime 2023/3/18
 */
@DubboService
public class RpcDemoServiceImpl implements RpcDemoService {
    @Override
    public String sayHello(String name) {
        System.out.println("Hello" + name + ", request from consumer:" + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name;
    }
}
