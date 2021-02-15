package com.demo;

import com.common.domain.event.BaseDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author daiyuanyang
 * @date 2021-02-15 16:34
 **/
@Data
@Builder
@AllArgsConstructor
public class DemoEvent extends BaseDomainEvent {
    private String msg;
}