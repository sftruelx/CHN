package com.mycompany.test;

import com.mycompany.util.StringUtil;

/**
 * Created by liaoxiang on 2016/6/20.
 */
public class Test {
    public static  void  main(String[] args){
        String p = "r*";
        String s = "r ";
        System.out.println(StringUtil.match(p, s));
    }
}
