package com.juzi.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.juzi.juapicommon.model.entity.UserInterfaceInfo;
import com.juzi.juapicommon.service.InnerUserInterfaceInfoService;
import com.juzi.project.common.ErrorCode;
import com.juzi.project.exception.BusinessException;
import com.juzi.project.mapper.UserInterfaceInfoMapper;
import com.juzi.project.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author codejuzi
 * @CreateTime 2023/3/18
 */
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;


    @Override
    public boolean hasInvokeCount(long userId, long interfaceInfoId) {
        if (userId <= 0 || interfaceInfoId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<UserInterfaceInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInterfaceInfo::getUserId, userId)
                .eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId)
                .gt(UserInterfaceInfo::getLeftNum, 0);
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoMapper.selectOne(queryWrapper);
        return userInterfaceInfo != null;
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public boolean invokeInterfaceCount(long userId, long interfaceId) {
        if (userId <= 0 || interfaceId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先查询记录是否存在
        LambdaQueryWrapper<UserInterfaceInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserInterfaceInfo::getUserId, userId)
                .eq(UserInterfaceInfo::getInterfaceInfoId, interfaceId);
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoMapper.selectOne(lambdaQueryWrapper);
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 统计接口次数，加锁，保证一次只有一个用户的请求来统计接口调用次数
        synchronized (Long.toString(userId).intern()) {
            return userInterfaceInfoMapper.invokeInterfaceCount(userId, interfaceId);
        }
    }
}
