package com.common.domain.event;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @description: Event Handler Annotation
 * @author: daiyuanyang
 * @create: 2021-01-17 21:48
 **/
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface EventHandler {
    Class<? extends BaseDomainEvent>[] value() default {};
}
