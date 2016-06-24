package com.mycompany.test.velocity;

import java.util.List;

/**
 * Created by liaoxiang on 2016/6/8.
 */
public class SysRightFactory {
    public static ClassDescritpt getInstance(){
        ClassDescritpt cd = new ClassDescritpt();
        cd.setClassLowCase("sysRight");
        cd.setClassName("SysRight");
        cd.setTableName("sys_right");
        List<Attribute> list = cd.getAttris();
        Attribute ar1 = new Attribute();
        ar1.setName("id");
        ar1.setNameUpCase("Id");
        ar1.setType("Long");
        ar1.setIndex(true);
        list.add(ar1);
        Attribute ar2 = new Attribute();
        ar2.setName("rightText");
        ar2.setNameUpCase("RightText");
        ar2.setType("String");
        ar2.setColumnName("right_text");
        ar2.setColumnNullable(true);
        ar2.setColumnUnique(false);
        ar2.setFieldValue(true);
        list.add(ar2);
        Attribute ar3 = new Attribute();
        ar3.setName("parentId");
        ar3.setType("Long");
        ar3.setNameUpCase("ParentId");
        ar3.setColumnName("parent_id");
        ar3.setFieldValue(true);
        list.add(ar3);
        Attribute ar4 = new Attribute("menuCode","MenuCode", "String",false,"menu_code",false,false,true);
        list.add(ar4);
        return cd;
    }
}
