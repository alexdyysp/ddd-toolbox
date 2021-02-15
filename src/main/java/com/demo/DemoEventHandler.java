package com.demo;

import com.common.domain.event.EventHandler;
import com.common.domain.event.IEventHandler;

/**
 * @author daiyuanyang
 * @date 2021-02-15 16:38
 **/
@EventHandler
public class DemoEventHandler implements IEventHandler<DemoEvent> {

    @Override
    public void handler(DemoEvent demoEvent) throws Exception {
        System.out.println(demoEvent.getMsg());
    }
}
