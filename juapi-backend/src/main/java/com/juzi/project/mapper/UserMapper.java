package com.juzi.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juzi.juapicommon.model.entity.User;

/**
 * @author codejuzi
 * @Entity com.juzi.project.model.domain.User
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户申请修改签名
     *
     * @param userId       用户id
     * @param newAccessKey 新的ak
     * @param newSecretKey 新的sk
     * @return true - 修改成功， false - 修改失败
     */
    boolean userChangeAccessKey(long userId, String newAccessKey, String newSecretKey);

}




