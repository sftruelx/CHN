package com.mycompany.webapp.controller;

import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/user*")
public class SysUserController {

    private static final String TOKEN = "myToken";

    @RequestMapping(value = "/showUserPage")
    public String queryUser() {
        return "sysUser";
    }

    @RequestMapping(value = "/send")
    public String send() {
        ModelAndView model = new ModelAndView();
        return "sends";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map listUser() {
        Map map = new HashMap();
        ArrayList list = new ArrayList();
        for (int i = 1; i <= 11; i++) {
            Map m = new HashMap();
            m.put("id", i);
            m.put("usrName", "name" + i);
            list.add(m);
        }

        map.put("current", 1);
        map.put("rowCount", 10);
        map.put("total", 12);
        map.put("rows", list);
        return map;
    }

}
