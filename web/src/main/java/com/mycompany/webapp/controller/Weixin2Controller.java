package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;

import javax.servlet.http.HttpServletRequest;

import static com.mycompany.util.WeixinUtil.getOpenid;

@RestController
@RequestMapping("/weixin2")
public class Weixin2Controller {
    private static final Logger log = LoggerFactory.getLogger(Weixin2Controller.class);
    private static final String appid = "wx41c9698b8348b7d6";
    private static final String secret = "e46ce562057ad2ddf10fc243328c7c12";

    /**
     * 创建菜单
     */
    @ResponseBody
    @RequestMapping("/createMenu")
    public String createMenu() {
        ApiConfig config = new ApiConfig(appid, secret);
        MenuAPI menuAPI = new MenuAPI(config);


        //先删除之前的菜单
        menuAPI.deleteMenu();
        Menu request = new Menu();
        //准备一级主菜单
        MenuButton main1 = new MenuButton();
        main1.setType(MenuType.CLICK);
        main1.setKey("main1");
        main1.setName("自助服务");

//        MenuButton main1sub1 = new MenuButton();
//	        main1sub1.setKey("main1sub1");
//	        main1sub1.setName("自助绑定");
//	        main1sub1.setType(MenuType.VIEW);
//	        main1sub1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx41c9698b8348b7d6&redirect_uri=http://hjwadmin.ngrok.cc/demo/bind&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        MenuButton main1sub2 = new MenuButton();
        main1sub2.setKey("main1sub2");
        main1sub2.setName("我的报告");
        main1sub2.setType(MenuType.VIEW);
        //String reportUrl = "https%3A%2F%2Fhjwadmin.ngrok.cc%2Fdemo%2Freport";
        String a = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http%3A%2F%2Fhjwadmin.ngrok.cc%2Fdemo%2Freport&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
        //String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + reportUrl + "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
        main1sub2.setUrl(a);

        List<MenuButton> main1list = new ArrayList<MenuButton>();
//        main1list.add(main1sub1);
        main1list.add(main1sub2);
        //将子菜单放入主菜单里   主菜单1
        main1.setSubButton(main1list);

        MenuButton main2 = new MenuButton();
        main2.setType(MenuType.CLICK);
        main2.setKey("main2");
        main2.setName("知识库");

        MenuButton main2sub1 = new MenuButton();
        main2sub1.setKey("main2sub1");
        main2sub1.setName("营养膳食");
        main2sub1.setType(MenuType.VIEW);
        main2sub1.setUrl("http://hjwadmin.ngrok.cc/demo/nutritionalDiet");

        MenuButton main2sub2 = new MenuButton();
        main2sub2.setKey("main1sub2");
        main2sub2.setName("智护训练");
        main2sub2.setType(MenuType.VIEW);
        main2sub2.setUrl("http://hjwadmin.ngrok.cc/demo/lore");

        List<MenuButton> main2list = new ArrayList<MenuButton>();
        main2list.add(main2sub1);
        main2list.add(main2sub2);
        //将子菜单放入主菜单里   主菜单2
        main2.setSubButton(main2list);

        MenuButton main3 = new MenuButton();
        main3.setType(MenuType.CLICK);
        main3.setKey("main3");
        main3.setName("医院介绍");

        MenuButton main3sub1 = new MenuButton();
        main3sub1.setKey("main3sub1");
        main3sub1.setName("医院介绍");
        main3sub1.setType(MenuType.VIEW);
        main3sub1.setUrl("http://www.szmch.net.cn/GAbout/index/134.html");

        MenuButton main3sub2 = new MenuButton();
        main3sub2.setKey("main3sub2");
        main3sub2.setName("科室介绍");
        main3sub2.setType(MenuType.VIEW);
        main3sub2.setUrl("http://www.szmch.net.cn/AboutKsjs/index.html");

        MenuButton main3sub3 = new MenuButton();
        main3sub3.setKey("main3sub3");
        main3sub3.setName("专家介绍");
        main3sub3.setType(MenuType.VIEW);
        main3sub3.setUrl("http://www.szmch.net.cn/AboutZjjs/index131.html");

        List<MenuButton> main3list = new ArrayList<MenuButton>();
        main3list.add(main3sub1);
        main3list.add(main3sub2);
        main3list.add(main3sub3);
        //将子菜单放入主菜单里   主菜单2
        main3.setSubButton(main3list);


        List<MenuButton> mainList = new ArrayList<MenuButton>();
        mainList.add(main1);
        mainList.add(main2);
        mainList.add(main3);
        //将主菜单加入请求对象
        request.setButton(mainList);
        log.debug(request.toJsonString());
        //创建菜单
        ResultType resultType = menuAPI.createMenu(request);
        log.debug(resultType.toString());
        return "ok";
    }

}