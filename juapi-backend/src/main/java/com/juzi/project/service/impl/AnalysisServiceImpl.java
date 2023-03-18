package com.juzi.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.juzi.juapicommon.model.entity.InterfaceInfo;
import com.juzi.juapicommon.model.entity.UserInterfaceInfo;
import com.juzi.juapicommon.model.vo.InvokeInterfaceInfoVO;
import com.juzi.project.common.ErrorCode;
import com.juzi.project.exception.BusinessException;
import com.juzi.project.mapper.UserInterfaceInfoMapper;
import com.juzi.project.service.AnalysisService;
import com.juzi.project.service.InterfaceInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author codejuzi
 * @CreateTime 2023/3/18
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Override
    public List<InvokeInterfaceInfoVO> listInvokeInterfaceInfo(int limit) {
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoMapper.listInvokeInterface(limit);
        if(userInterfaceInfoList == null || userInterfaceInfoList.isEmpty()) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        // 根据接口id查询接口名称
        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap = userInterfaceInfoList.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        LambdaQueryWrapper<InterfaceInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(InterfaceInfo::getId, interfaceInfoIdObjMap.keySet());
        List<InterfaceInfo> interfaceInfoList = interfaceInfoService.list(queryWrapper);
        if(CollectionUtils.isEmpty(interfaceInfoList)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        // 重建list，设置调用次数
        return interfaceInfoList.stream().map(interfaceInfo -> {
            InvokeInterfaceInfoVO invokeInterfaceInfoVO = new InvokeInterfaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, invokeInterfaceInfoVO);
            int totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            invokeInterfaceInfoVO.setTotalNum(totalNum);
            return invokeInterfaceInfoVO;
        }).collect(Collectors.toList());
    }
}
