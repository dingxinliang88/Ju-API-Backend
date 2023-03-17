package com.juzi.project.model.dto.userinterfaceinfo;

import com.juzi.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author juzi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInterfaceInfoQueryRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = 5783089904508412642L;

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

}