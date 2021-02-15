package com.common.domain.event;

/**
 * @description: Domain Event Publisher Interface
 * @author: daiyuanyang
 * @create: 2021-01-17 21:00
 **/
public interface IDomainEventPublisher {

    void publish(BaseDomainEvent baseDomainEvent) throws Exception;
}
