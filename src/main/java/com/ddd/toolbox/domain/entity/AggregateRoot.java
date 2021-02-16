package com.ddd.toolbox.domain.entity;

import com.ddd.toolbox.domain.event.BaseDomainEvent;
import com.ddd.toolbox.domain.event.BaseDomainEventContext;
import com.ddd.toolbox.domain.event.DefaultLocalDomainPublisher;
import com.ddd.toolbox.domain.event.IDomainEventPublisher;
import com.ddd.toolbox.domain.exceptions.EventException;
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
public class AggregateRoot extends Entity {

    @Resource(name = "defaultLocalDomainPublisher")
    private DefaultLocalDomainPublisher defaultLocalDomainPublisher;

    /**
     * create domain event and publish event
     * need override createDomainEventWithContext first
     * @param  domainEventClass  domain event type
     * @param  context           domain event context
     * @throws Exception         exception
     */
    public void createAndPublishEvent(Class<? extends BaseDomainEvent> domainEventClass, BaseDomainEventContext context)
            throws Exception {
        BaseDomainEvent event = domainEventClass.cast(createDomainEventWithContext(domainEventClass, context));
        if(Objects.isNull(event)) {
            throw new EventException("create domain event failure");
        }
        publishDefault(event);
    }

    /**
     * create DomainEvent by args
     * @param   domainEventClass  domian class tpye
     * @param   args              params
     * @return  BaseDomainEvent
     * @throws  EventException
     */
    public BaseDomainEvent createDomainEvent(Class<? extends BaseDomainEvent> domainEventClass, Objects... args)
            throws EventException {
        throw new EventException("Aggregate need override method[createDomainEvent]");
    }

    /**
     * create domain event from context
     * @param   domainEventClass  domain event type
     * @param   context           domain context
     * @return  BaseDomainEvent
     */
    public BaseDomainEvent createDomainEventWithContext(Class<? extends BaseDomainEvent> domainEventClass,
                                                        BaseDomainEventContext context) {
        throw new EventException("Aggregate need override method[createDomainEvent]");
    }

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

    /**
     * default event publish method
     * @param  event        domain event
     * @throws Exception    exception
     */
    public void publishDefault(BaseDomainEvent event) throws Exception {
        if(Objects.isNull(event)) {
            return;
        }
        IDomainEventPublisher udfPublisher = event.customDomainEventPublisher();
        if(Objects.nonNull(udfPublisher)) {
            udfPublisher.publish(event);
        } else {
            defaultLocalDomainPublisher.publish(event);
        }
    }

}
