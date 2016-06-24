package com.mycompany.model.sys;

import java.util.Map;

/**
 * Created by wsd-pc on 2016/6/2.
 */
public class ActionMsg {

    private boolean success;
    private String msg;
    private Map data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
