package com.common;

import com.common.factory.DomainFactory;
import com.demo.DemoAgg;
import com.demo.SpringDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: daiyuanyang
 * @create: 2021-01-18 00:04
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDemoApplication.class)
public class SpringTest {

    @Test
    public void  testAggPublishEvent() {
        DemoAgg demoAgg = DomainFactory.createAgg(DemoAgg.class);
        demoAgg.createAEvent("msg from agg");
    }
}
