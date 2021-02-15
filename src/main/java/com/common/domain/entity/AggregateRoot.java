package com.common.domain.entity;

import com.common.domain.event.BaseDomainEvent;
import com.common.domain.event.DefaultLocalDomainPublisher;
import com.common.domain.event.IDomainEventPublisher;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 *
 * @description: Aggregate Root Object
 * @author: daiyuanyang
 * @create: 2021-01-17 20:49
 **/
@Slf4j
abstract
public class AggregateRoot extends Entity{

    @Resource
    private DefaultLocalDomainPublisher defaultLocalDomainPublisher;


    public void publishEvent(List<BaseDomainEvent> events) throws Exception {
        for(BaseDomainEvent event : events) {
            publishEvent(event);
        }
    }

    public void publishEvent(BaseDomainEvent event) throws Exception {
        if(Objects.isNull(event)) {
            return;
        }
        defaultLocalDomainPublisher.publish(event);
        IDomainEventPublisher udfPublisher = event.customDomainEventPublisher();
        if(Objects.nonNull(udfPublisher)) {
            udfPublisher.publish(event);
        }
    }

}
