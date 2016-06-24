package com.mycompany.model.sys;

import java.util.Comparator;

/**
 * Created by liaoxiang on 2016/6/7.
 */
public class SysRightComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        SysRight s1 = (SysRight)o1;
        SysRight s2 = (SysRight)o2;
        if(s1.getMenuCode() != null && s2.getMenuCode() != null){
            return s1.getMenuCode().compareTo(s2.getMenuCode());
        }
        return 0;
    }
}
