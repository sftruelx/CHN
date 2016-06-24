package com.mycompany.util;

import org.junit.Test;

/**
 * Created by wsd on 2016/6/14.
 */
public class GeneratorIdUtilTest {

    @Test
    public void getId() throws Exception {
        for(int i=1;i<1000;i++) {
            Long id = GeneratorIdUtil.getId();
            System.out.println(id+"  /  "+System.currentTimeMillis());
        }
    }

}
