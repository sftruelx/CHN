package com.mycompany.model.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsd on 2016/5/30.   ztree树
 */
public class Ztree {

    private Long id;
    private String code;
    private String name;
    private String describe;
    private String codeParent;
    private String remark;
    private Integer delabled;
    private Integer type;
    public List<Ztree> children=new ArrayList<Ztree>();
    private Boolean checked;
    private Boolean isParent;
    private Boolean open;

    public void addChildren(Ztree ztree){
        this.children.add(ztree);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ztree> getChildren() {
        return children;
    }

    public void setChildren(List<Ztree> children) {
        this.children = children;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }


    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCodeParent() {
        return codeParent;
    }

    public void setCodeParent(String codeParent) {
        this.codeParent = codeParent;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDelabled() {
        return delabled;
    }

    public void setDelabled(Integer delabled) {
        this.delabled = delabled;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
