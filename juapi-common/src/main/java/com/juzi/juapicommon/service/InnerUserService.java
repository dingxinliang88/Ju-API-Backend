package com.juzi.juapicommon.service;

import com.juzi.juapicommon.model.entity.User;

/**
 * 供服务端调用的公共接口
 *
 * @author codejuzi
 * @CreateTime 2023/3/18
 */
public interface InnerUserService {

    /**
     * 根据accessKey获取用户
     *
     * @param accessKey ak
     * @return 用户信息
     */
    User getInvokeUser(String accessKey);
}
