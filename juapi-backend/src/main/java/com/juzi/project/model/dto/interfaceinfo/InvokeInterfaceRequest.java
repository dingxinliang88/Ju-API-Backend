package com.juzi.project.model.dto.interfaceinfo;

import lombok.Data;

/**
 * 调用接口请求参数
 * @author codejuzi
 * @CreateTime 2023/3/14
 */
@Data
public class InvokeInterfaceRequest {

    /**
     * 接口id
     */
    private Long id;

    /**
     * 请求参数
     */
    private String requestParam;
}
