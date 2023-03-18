package com.juzi.juapicommon.service;

import com.juzi.juapicommon.model.entity.InterfaceInfo;

/**
 * 供服务端调用的公共接口
 *
 * @author codejuzi
 * @CreateTime 2023/3/18
 */
public interface InnerInterfaceInfoService {

    /**
     * 根据路径(url)、请求方法(method)获取接口信息
     *
     * @param url   路径
     * @param method 请求方法
     * @return 接口信息
     */
    InterfaceInfo getInvokeInterfaceInfo(String url, String method);
}
