package com.baogex.springframework.context;

import com.baogex.springframework.beans.factory.HierarchicalBeanFactory;
import com.baogex.springframework.beans.factory.ListableBeanFactory;
import com.baogex.springframework.core.io.ResourceLoader;

/**
 * @author : baogex.com
 * @since : 2021-08-04
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
