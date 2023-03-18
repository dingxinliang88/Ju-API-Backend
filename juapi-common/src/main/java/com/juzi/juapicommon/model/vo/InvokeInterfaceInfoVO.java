package com.juzi.juapicommon.model.vo;

import com.juzi.juapicommon.model.entity.InterfaceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author codejuzi
 * @CreateTime 2023/3/18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InvokeInterfaceInfoVO extends InterfaceInfo implements Serializable {
    private static final long serialVersionUID = 2548418958865843632L;

    /**
     * 接口调用次数
     */
    private Integer totalNum;
}
