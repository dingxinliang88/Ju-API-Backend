package com.juzi.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzi.juapicommon.model.entity.UserInterfaceInfo;
import com.juzi.juapicommon.model.enums.UserInterfaceInfoStatusEnum;
import com.juzi.project.common.ErrorCode;
import com.juzi.project.exception.BusinessException;
import com.juzi.project.mapper.UserInterfaceInfoMapper;
import com.juzi.project.service.UserInterfaceInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author codejuzi
 * @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service实现
 * @createDate 2023-03-17 08:56:33
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long userId = userInterfaceInfo.getUserId();
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Integer totalNum = userInterfaceInfo.getTotalNum();
        Integer leftNum = userInterfaceInfo.getLeftNum();
        Integer status = userInterfaceInfo.getStatus();

        // 创建时，所有参数必须非空
        if (add) {
            if (ObjectUtils.anyNull(userId, interfaceInfoId, totalNum, leftNum, status)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }

        if (status != null && !UserInterfaceInfoStatusEnum.getValues().contains(status)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }

}




