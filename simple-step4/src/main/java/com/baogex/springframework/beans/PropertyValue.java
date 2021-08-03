package com.baogex.springframework.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 属性值存储
 *
 * @author : baogex.com
 * @since : 2021-08-03
 */
@Getter
@Setter
@AllArgsConstructor
public class PropertyValue {
    private String name;
    private Object value;
}
