package com.juzi.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juzi.juapicommon.model.entity.UserInterfaceInfo;

/**
 * @author codejuzi
 * @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Mapper
 * @createDate 2023-03-17 08:56:33
 * @Entity com.juzi.project.model.entity.UserInterfaceInfo
 */
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    /**
     * 统计接口调用次数
     *
     * @param userId      调用用户id
     * @param interfaceId 调用接口id
     * @return 是否统计成功
     */
    boolean invokeInterfaceCount(long userId, long interfaceId);

}




