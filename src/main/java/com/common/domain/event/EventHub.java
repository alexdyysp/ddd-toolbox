package com.common.domain.event;

import com.common.domain.exceptions.EventException;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: Domain Events Hub Center
 * @author: daiyuanyang
 * @create: 2021-01-17 21:15
 **/
@Slf4j
@Component
public class EventHub {

    private static final String DEFAULT_HANDLER_METHOD_NAME = "handler";

    private final Map<Class<? extends IEvent>, List<IEventHandler<? extends IEvent>>> eventRegisterMap =
            new HashMap(16);

    @Resource
    private List<IEventHandler<? extends IEvent>> eventHandlers;

    /**
     * Init Event Hub By Registering all Events and EventHandlers
     */
    @PostConstruct
    public void init(){
        eventHandlers.forEach(this::register);
    }

    private void register(IEventHandler<? extends IEvent> eventHandler){
        List<Class<? extends IEvent>> eventClassList = getEvents(eventHandler.getClass());
        eventClassList.forEach(clazz -> {
            List<IEventHandler<? extends IEvent>> eventHandlers = eventRegisterMap.computeIfAbsent(clazz,
                    k->Lists.newArrayList());
            eventHandlers.add(eventHandler);
            log.info("Domain Event Register[new Register]: [Event {}] has [handler {}]", clazz, eventHandler);
        });
    }

    private List<Class<? extends IEvent>> getEvents(Class<?> eventHandler){
        EventHandler annotation = eventHandler.getAnnotation(EventHandler.class);
        if(Objects.nonNull(annotation)){
            Class<? extends BaseDomainEvent>[] values = annotation.value();
            if(values.length > 0){
                return Lists.newArrayList(values);
            }
        }

        Method[] methods = eventHandler.getDeclaredMethods();
        List<Class<? extends IEvent>> events = Arrays.stream(methods)
                .filter(method -> Objects.nonNull(method) &&
                        DEFAULT_HANDLER_METHOD_NAME.equals(method.getName()) && !method.isBridge())
                .map(this::checkAndGetEventClass)
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(events)) {
            throw new EventException("Empty Event handler: " + eventHandler + DEFAULT_HANDLER_METHOD_NAME + "is not registered");
        }
        return events;    }

    /**
     * Get Event Handler
     * @param eventClass Event Class
     * @return IEventHandler List
     */
    public List<IEventHandler<? extends IEvent>> getEventHandlers(Class<? extends IEvent> eventClass){
        List<IEventHandler<? extends IEvent>> eventHandlers = eventRegisterMap.get(eventClass);
        if(CollectionUtils.isEmpty(eventHandlers)){
            log.warn("[Event {}] has not [EventHandler]", eventClass);
        }
        return eventHandlers;
    }

    private Class<? extends IEvent> checkAndGetEventClass(Method method) {
        Class<? extends IEvent>[] exeParams = (Class<? extends IEvent>[]) method.getParameterTypes();
        if(exeParams.length == 0) {
            throw new EventException("Execute method [" + method.getDeclaringClass() + "] have none params");
        }
        if(!IEvent.class.isAssignableFrom(exeParams[0])) {
            throw new EventException("Execute method [" + method.getDeclaringClass() + "] is not subClass of Event");
        }
        return exeParams[0];
    }


}
