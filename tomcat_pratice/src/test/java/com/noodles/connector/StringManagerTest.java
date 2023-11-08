package com.noodles.connector;

import com.noodles.ch03.connector.http.Constants;
import com.noodles.ch03.utils.StringManager;
import org.junit.Test;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/7 11:45
 **/
public class StringManagerTest {

    protected StringManager sm = StringManager.getManager(Constants.Package);

    /**
     * target 目录，com.noodles.ch03.connector.http 中需要存在 LocalStrings.properties 文件
     */
    @Test
    public void testStringManager() {
        System.out.println(sm.getString("httpProcessor.parseHeaders.colon"));
    }
}
