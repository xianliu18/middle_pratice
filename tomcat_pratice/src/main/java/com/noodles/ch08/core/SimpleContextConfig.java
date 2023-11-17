package com.noodles.ch08.core;

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
    public void lifecycleEvent(LifecycleEvent lifecycleEvent) {
        if (Lifecycle.START_EVENT.equals(lifecycleEvent.getType())) {
            Context context = (Context)lifecycleEvent.getLifecycle();
            context.setConfigured(true);
        }
    }
}
