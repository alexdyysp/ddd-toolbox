package com.ddd.toolbox.domain.event;

import com.ddd.toolbox.domain.entity.AggregateRoot;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 朴素事件发布器
 * @author daiyuanyang
 * @date 2021-05-05 21:15
 **/
@Component
@Slf4j
public class PlainEventPublisher extends AggregateRoot {

    public void simplePublish(@NonNull BaseDomainEvent event) {
        try {
            publishDefault(event);
        } catch (Exception e) {
            log.error("发送领域事件: {} 时发生异常: ", this.getClass().getSimpleName(), event, e);
        }
    }

}
