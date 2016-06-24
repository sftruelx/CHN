package com.mycompany.util;

import org.apache.commons.lang.time.*;
import org.junit.Test;

/**
 * Created by wsd-pc on 2016/6/21.
 */
public class DateUtilsTest {

    @Test
    public void getDateTest(){

        System.out.println(DateUtils.now());
        System.out.println(DateUtils.formatSimpleDate(DateUtils.now()));
        System.out.println(DateUtils.formatStringDate("2016-01-01"));

    }


}
