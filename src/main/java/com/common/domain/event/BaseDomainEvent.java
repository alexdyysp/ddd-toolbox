package com.common.domain.event;

/**
 * @description: domain event
 * @author: daiyuanyang
 * @create: 2021-01-17 20:55
 **/
abstract
public class BaseDomainEvent implements IEvent{
    public IDomainEventPublisher customDomainEventPublisher(){
        return null;
    }
}
