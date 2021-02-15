package com.common.domain.event;

/**
 * @description:
 * @author: daiyuanyang
 * @create: 2021-01-17 22:12
 **/
public class EventRegisterException extends RuntimeException {
    public EventRegisterException(String msg){
        super(msg);
    }

    public EventRegisterException(String msg, Throwable throwable){
        super(msg, throwable);
    }

    public EventRegisterException(Throwable throwable){
        super(throwable);
    }
}
