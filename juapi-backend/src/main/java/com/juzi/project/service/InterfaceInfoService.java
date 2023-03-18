package com.juzi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzi.juapicommon.model.entity.InterfaceInfo;

/**
 * @author codejuzi
 * @description 针对表【interface_info(接口信息表)】的数据库操作Service
 * @createDate 2023-03-05 10:24:48
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 校验参数
     *
     * @param interfaceInfo
     * @param add 是否是新增
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
