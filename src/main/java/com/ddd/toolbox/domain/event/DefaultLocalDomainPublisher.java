package com.ddd.toolbox.domain.event;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author daiyuanyang
 * @date 2021-02-13 18:29
 **/
@Component
public class DefaultLocalDomainPublisher implements IDomainEventPublisher {

    @Resource
    private EventBus syncEventBus;

    @Override
    public void publish(BaseDomainEvent baseDomainEvent) throws Exception {
        syncEventBus.dispatch(baseDomainEvent);
    }
}
