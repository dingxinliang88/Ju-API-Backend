package com.juzi.project.controller;

import com.juzi.juapicommon.model.vo.InvokeInterfaceInfoVO;
import com.juzi.project.annotation.AuthCheck;
import com.juzi.project.common.BaseResponse;
import com.juzi.project.common.ErrorCode;
import com.juzi.project.common.ResultUtils;
import com.juzi.project.exception.BusinessException;
import com.juzi.project.service.AnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.juzi.project.constant.CommonConstant.DEFAULT_TOP_INTERFACE_NUM;

/**
 * 统计分析控制器
 *
 * @author codejuzi
 * @CreateTime 2023/3/18
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private AnalysisService analysisService;

    @AuthCheck(mustRole = "admin")
    @GetMapping("/top/interface/list/{num}")
    public BaseResponse<List<InvokeInterfaceInfoVO>> listTopInvokeInterfaceInfo(@PathVariable(value = "num") int limit) {
        if (limit <= 0) {
            limit = DEFAULT_TOP_INTERFACE_NUM;
        }
        List<InvokeInterfaceInfoVO> invokeInterfaceInfoVOList = analysisService.listInvokeInterfaceInfo(limit);
        if (CollectionUtils.isEmpty(invokeInterfaceInfoVOList)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultUtils.success(invokeInterfaceInfoVOList);
    }
}
