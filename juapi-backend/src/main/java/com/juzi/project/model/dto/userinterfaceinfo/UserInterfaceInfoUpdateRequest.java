package com.juzi.project.model.dto.userinterfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新请求
 *
 * @author codejuzi
 * @TableName product
 */
@Data
public class UserInterfaceInfoUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1240042015566459287L;

    /**
     * id
     */
    private Long id;

    /**
     * 调用用户id
     */
    private Long userId;

    /**
     * 接口id
     */
    private Long interfaceInfoId;

    /**
     * 总调用次数
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;

    /**
     * 0 - 正常，1 - 禁用
     */
    private Integer status;

    /**
     * 是否删除(0-未删, 1-已删)
     */
    private Integer isDelete;

}