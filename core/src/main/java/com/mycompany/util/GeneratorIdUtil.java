package com.mycompany.util;

/**
 * Created by wsd on 2016/6/14.
 */
//发号器
public class GeneratorIdUtil {

    private static long INFOID_FLAG = 1260000000000L;
    protected static int SERVER_ID = 1;

    //高并发、分布式系统中提供高效生成不重复唯一的一个ID
    public static synchronized long getId() throws Exception {
        if(SERVER_ID <= 0)
            throw new Exception("server id is error,please check config file!");
        long infoid = System.currentTimeMillis() - INFOID_FLAG;
        infoid=(infoid<<7)| SERVER_ID;
        Thread.sleep(1);
        return infoid;
    }

}
