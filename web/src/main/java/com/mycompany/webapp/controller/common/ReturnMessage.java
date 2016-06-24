package com.mycompany.webapp.controller.common;

/**
 * Created by liaoxiang on 2016/6/17.
 */
public class ReturnMessage {

    private Integer flag;
    private String message;
    private Object obj;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
