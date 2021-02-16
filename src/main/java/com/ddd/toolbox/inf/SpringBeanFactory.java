package com.ddd.toolbox.inf;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @description: Collect Beans of Spring
 * @author: daiyuanyang
 * @create: 2021-01-17 22:42
 **/
@Component
public class SpringBeanFactory implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        synchronized (this) {
            if(Objects.isNull(SpringBeanFactory.context)) {
                SpringBeanFactory.context = applicationContext;
            }
        }
    }

    /**
     *
     * @param type Object Class Type
     * @param <T>  Bean
     * @return  Bean
     */
    public static <T> T createEmptyBean(Class<T> type) {
        return context.getBean(type);
    }

    /**
     *
     * @param type Object Class Type
     * @param <T>  Bean
     * @return  Bean
     */
    public static <T> T getProxy(Class<T> type) {
        return context.getBean(type);
    }

    /**
     *
     * @param <T>  Bean
     * @param type Object Class Type
     * @return  Bean
     */
    public static <T> Map<String, T> getProxyMap(Class<T> type) {
        return context.getBeansOfType(type);
    }

}
