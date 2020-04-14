package com.project.project5.config.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName TestEvent
 * @date 2020/4/14 17:33
 */
public class TestEvent extends ApplicationEvent {

    public TestEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
