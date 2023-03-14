package com.juzi.project.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用的id请求类
 *
 * @author codejuzi
 */
@Data
public class IdRequest implements Serializable {
    private static final long serialVersionUID = 4831203014899473630L;

    /**
     * 通用的请求id
     */
    private Long id;
}
