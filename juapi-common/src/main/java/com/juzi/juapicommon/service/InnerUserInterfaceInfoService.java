package com.juzi.juapicommon.service;

/**
 * 供服务端调用的公共接口
 *
 * @author codejuzi
 * @CreateTime 2023/3/18
 */
public interface InnerUserInterfaceInfoService {

    /**
     * 查询用户是否还有接口调用次数
     * @param userId 调用用户id
     * @param interfaceInfoId 调用接口id
     * @return true - 还剩余调用次数， false - 没有剩余调用次数
     */
    boolean hasInvokeCount(long userId, long interfaceInfoId);


    /**
     * 根据userId、interfaceInfoId计数，调用成功后调用次数+1
     * @param userId 调用用户id
     * @param interfaceInfoId 调用接口id
     * @return true - 统计成功， false - 统计失败
     */
    boolean invokeInterfaceCount(long userId, long interfaceInfoId);
}
