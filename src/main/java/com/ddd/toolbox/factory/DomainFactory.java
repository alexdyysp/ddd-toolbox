package com.ddd.toolbox.factory;

import com.ddd.toolbox.domain.entity.Entity;
import com.ddd.toolbox.inf.SpringBeanFactory;

/**
 * @author daiyuanyang
 * @date 2021-02-15 16:42
 **/
public class DomainFactory {

    public static <T extends Entity> T createEntity(Class<T> entityClz) {
        return SpringBeanFactory.createEmptyBean(entityClz);
    }

    public static <T extends Entity> T createAgg(Class<T> entityClz) {
        return SpringBeanFactory.createEmptyBean(entityClz);
    }
}
