# ddd-toolbox
A common toolbox for practise domain driven model 

- simplifies development for ddd in java
- supply basic infrastructure module
    - Aggregate Root
    - Entity
    - Domain Factory
    - EventBus

## How to Use
Define a aggregate:
```java
@Component
@Scope("prototype")
public class DemoAgg extends AggregateRoot{
    ......
}
```
Use the aggregate named "DemoAgg"
```java
DomainFactory.createAgg(DemoAgg.class);
```

EventBus in 3 Steps
1. Define events
    ```java
    @Data
    @Builder
    @AllArgsConstructor
    public class DemoEvent extends BaseDomainEvent {
        private String msg;
    }
    ```
2. define handler
    ```java
    @EventHandler
    public class DemoEventHandler implements IEventHandler<DemoEvent> {
    
        @Override
        public void handler(DemoEvent demoEvent) throws Exception {
            System.out.println(demoEvent.getMsg());
        }
    }
    ```
3. create and publish
    ```java
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
    ```

## Add ddd-toolbox to you project
Via Maven:
```xml
    <dependency>
        <groupId>com.ddd.java.toolbox</groupId>
        <artifactId>ddd-toolbox</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```