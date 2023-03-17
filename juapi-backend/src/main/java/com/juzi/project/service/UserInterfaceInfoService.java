package com.juzi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzi.project.model.entity.UserInterfaceInfo;

/**
 * @author codejuzi
 * @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service
 * @createDate 2023-03-17 08:56:33
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    /**
     * 校验参数
     *
     * @param userInterfaceInfo
     * @param add               是否是新增
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 统计接口调用次数
     *
     * @param userId      调用用户id
     * @param interfaceId 调用接口id
     * @return 是否统计成功
     */
    boolean invokeInterfaceCount(long userId, long interfaceId);
}
