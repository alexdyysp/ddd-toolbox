package com.demo;

import com.ddd.toolbox.domain.entity.AggregateRoot;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author daiyuanyang
 * @date 2021-02-15 16:33
 **/
@Component
@Scope("prototype")
public class DemoAgg extends AggregateRoot {

    public void createAEvent(String data) {
        DemoEvent event = new DemoEvent(data);
        try {
            publishEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
