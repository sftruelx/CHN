package com.mycompany.webapp.controller;

import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liaoxiang on 2016/4/11.
 *
 *
 */

@Controller
@RequestMapping("/mytest")
public class TestController {

    @RequestMapping("/custom-toolbar")
    public String showTest(){
//        FilterSecurityInterceptor
        return "/mytest/custom-toolbar";
    }

    @RequestMapping("/from-html")
    public String showTable(){
//        FilterSecurityInterceptor
        return "/mytest/from-html";
    }

    @ResponseBody
    @RequestMapping("/getsome")
    public Map getSome(){
        Map map = new HashMap();
        map.put("one","first");
        map.put("two","sldkfj");
        return map;
    }

}
