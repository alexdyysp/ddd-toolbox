package com.ddd.toolbox.domain.event;

/**
 * @description:
 * @author: daiyuanyang
 * @create: 2021-01-17 21:02
 **/
public interface IEventHandler<E extends IEvent> {

    void handler(E e) throws Exception;
}
