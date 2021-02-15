package com.common.domain.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: Domain Event Bus
 * @author: daiyuanyang
 * @create: 2021-01-17 21:09
 **/
@Slf4j
@Component
public class EventBus {

    @Resource
    private EventHub eventHub;

    /**
     * Dispatch Domain Event
     * @param event Domain Event
     * @return Dispatch Result Status: success or failure
     */
    public Boolean dispatch(IEvent event){
        List<? extends IEventHandler> handlerList = eventHub.getEventHandlers(event.getClass());
        for(IEventHandler handler: handlerList){
            try {
                handler.handler(event);
            }catch (Exception e){
                log.error("EventBus [dispatch error]: [Event {}] handle [EventHandler {}] error: ", event, handler, e);
                return false;
            }
        }
        return true;
    }
}
