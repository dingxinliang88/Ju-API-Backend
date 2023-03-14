package com.juzi.project.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 接口状态枚举类
 *
 * @author juzi
 */
public enum InterfaceInfoStatusEnum {

    /**
     * interface closed
     */
    OFFLINE("下线", 0),

    /**
     * interface opened
     */
    ONLINE("上线", 1),
    ;

    private final String text;

    private final int value;

    InterfaceInfoStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return 值列表
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
