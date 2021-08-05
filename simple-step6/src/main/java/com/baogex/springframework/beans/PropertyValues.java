package com.baogex.springframework.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author : baogex.com
 * @since : 2021-08-03
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>(16);

    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public void addPropertyValue(PropertyValue value) {
        propertyValueList.add(value);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : propertyValueList) {
            if (Objects.equals(propertyValue.getName(), propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }
}
