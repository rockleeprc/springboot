package org.example10.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 等同于 context.addApplicationListener(event -> System.out.println(event));
 */
@Component
public class ApplicationEventListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("ApplicationEvent...." + event.getSource());
    }
}
