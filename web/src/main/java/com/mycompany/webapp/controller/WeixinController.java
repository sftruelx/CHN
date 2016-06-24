package com.mycompany.webapp.controller;

import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.handle.MessageHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.NewsMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${吴愿} on 2016/4/15.
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController extends WeixinControllerSupport {

    private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
    private static final String TOKEN = "myToken";

    //设置TOKEN，用于绑定微信服务器
    @Override
    protected String getToken() {
        return TOKEN;
    }

    @RequestMapping(value = "/test")
    public String queryUser() {
        return "sysUser";
    }

    //使用安全模式时设置：APPID
    //不再强制重写，有加密需要时自行重写该方法
    @Override
    protected String getAppId() {
        return null;
    }

    //使用安全模式时设置：密钥
    //不再强制重写，有加密需要时自行重写该方法
    @Override
    protected String getAESKey() {
        return null;
    }

    //重写父类方法，处理对应的微信消息
    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent();
        log.debug("用户发送到服务器的内容:{}", content);
        return new TextMsg("服务器回复用户消息!");
    }

    /*1.1版本新增，重写父类方法，加入自定义微信消息处理器
     *不是必须的，上面的方法是统一处理所有的文本消息，如果业务觉复杂，上面的会显得比较乱
     *这个机制就是为了应对这种情况，每个MessageHandle就是一个业务，只处理指定的那部分消息
     */
    @Override
    protected List<MessageHandle> initMessageHandles() {
        List<MessageHandle> handles = new ArrayList<MessageHandle>();
//        handles.add(new MyMessageHandle());
        return handles;
    }

    //1.1版本新增，重写父类方法，加入自定义微信事件处理器，同上
    @Override
    protected List<EventHandle> initEventHandles() {
        List<EventHandle> handles = new ArrayList<EventHandle>();
//        handles.add(new MyEventHandle());
        return handles;
    }

    @Override
    protected BaseMsg handleSubscribe(BaseEvent event) {
        NewsMsg newsMsg = new NewsMsg();
        String title = "你的账号已关注儿童健康微信公众号，";
        String description = "请点击查看全文或点击图片绑定账号再进行业务查询。";
        String picUrl = "http://hjwadmin.ngrok.cc/images/banner2.jpg";
        String url = "http://hjwadmin.ngrok.cc/demo/bind?openId=" + event.getFromUserName();
        newsMsg.add(title, description, picUrl, url);
        //log.debug(DdJsonUtils.toJSONString(newsMsg));
        return newsMsg;
    }

}
