package com.mycompany.test.velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaoxiang on 2016/6/8.
 */
public class ClassDescritpt {
    private String tableName;
    private String className;
    private String classLowCase;
    private List<Attribute> attris = new ArrayList<Attribute>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassLowCase() {
        return classLowCase;
    }

    public void setClassLowCase(String classLowCase) {
        this.classLowCase = classLowCase;
    }

    public List<Attribute> getAttris() {
        return attris;
    }

    public void setAttris(List<Attribute> attris) {
        this.attris = attris;
    }
}
