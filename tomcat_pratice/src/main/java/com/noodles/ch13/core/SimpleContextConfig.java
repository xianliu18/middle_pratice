package com.noodles.ch13.core;

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/17 11:32
 **/
public class SimpleContextConfig implements LifecycleListener {

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        if (Lifecycle.START_EVENT.equals(event.getType())) {
            Context context = (Context)event.getLifecycle();
            context.setConfigured(true);
        }
    }

}
