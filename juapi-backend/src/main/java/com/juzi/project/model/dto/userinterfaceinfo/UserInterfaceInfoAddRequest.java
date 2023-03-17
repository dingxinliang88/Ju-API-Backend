package com.juzi.project.model.dto.userinterfaceinfo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口信息增加接口请求参数
 *
 * @author codejuzi
 */
@Data
public class UserInterfaceInfoAddRequest implements Serializable {
    private static final long serialVersionUID = -5074886936554492648L;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

}
