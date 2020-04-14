package com.project.project5.config.listener;

import com.project.project5.config.listener.event.TestEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName EventTestListener
 * @date 2020/4/14 17:37
 */
@Component
public class EventTestListener implements ApplicationListener<TestEvent> {

    @Override
    public void onApplicationEvent(TestEvent testEvent) {
        //具体业务
        String msg = testEvent.getMsg();
        System.err.println(msg);
    }
}
