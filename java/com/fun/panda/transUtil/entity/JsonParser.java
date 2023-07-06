package com.fun.panda.transUtil.entity;

import lombok.Data;

/**
 * json树信息 - 平铺
 * @date: 2023-06-20
 * @version: V1.0
 */
@Data
public class JsonParser {

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 参考值
     */
    private Object defaultValue;

    /**
     * jsonpath
     */
    private String jsonPath;

    /**
     * 父节点key值
     */
    private String parentNode;

}
