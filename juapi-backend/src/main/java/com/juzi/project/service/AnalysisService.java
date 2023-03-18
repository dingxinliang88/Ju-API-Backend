package com.juzi.project.service;

import com.juzi.juapicommon.model.vo.InvokeInterfaceInfoVO;

import java.util.List;

/**
 * 统计分析
 *
 * @author codejuzi
 * @CreateTime 2023/3/18
 */
public interface AnalysisService {

    /**
     * 统计分析接口调用情况
     *
     * @param limit top limit
     * @return vo list
     */
    List<InvokeInterfaceInfoVO> listInvokeInterfaceInfo(int limit);
}
