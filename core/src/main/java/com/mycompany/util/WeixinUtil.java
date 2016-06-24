package com.mycompany.util;

import com.alibaba.fastjson.JSON;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wsd
 */
public class WeixinUtil {

    /**
     * descript:  要求访问的url由微信的oauth2重定向到要访问的地址，方法带HttpServletRequest 传进来，返回微信用户的openid
     *
     * @param appid
     * @param secret
     * @return openid
     * @author wsd  2016-04-21
     */
    public static String getOpenid(String appid, String secret, HttpServletRequest request) {
        String code = request.getParameter("code");
        String oauth2url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code";
        BaseResponse a = NetWorkCenter.post(oauth2url, null);
        if (StringUtils.isNotEmpty(a.getErrcode()) && !StringUtils.equals("0", a.getErrcode())) {
            return "错误码：" + a.getErrcode();
        }
        String openid = JSON.parseObject(a.getErrmsg()).getString("openid");
        return openid;
    }


}
