package com.noodles.ch03.connector.http;

import java.io.File;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/6 16:13
 **/
public class Constants {
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "tomcat-webroot";
    public static final String Package = "com.noodles.ch03.connector.http";
    public static final int DEFAULT_CONNECTION_TIMEOUT = 60000;
    public static final int PROCESSOR_IDLE = 0;
    public static final int PROCESSOR_ACTIVE = 1;
}
