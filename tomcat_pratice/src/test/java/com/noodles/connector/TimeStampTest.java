package com.noodles.connector;

import org.junit.Test;

import java.sql.Timestamp;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/10 12:10
 **/
public class TimeStampTest {
    @Test
    public void timeTest() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String tsString = ts.toString().substring(0, 19);
        String tsDate = tsString.substring(0, 10);
        System.out.println("tsString: " + tsString);
        System.out.println(tsDate);
    }
}
