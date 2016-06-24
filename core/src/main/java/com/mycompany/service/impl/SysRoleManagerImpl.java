package com.mycompany.service.impl;

import com.mycompany.dao.sys.SysRoleDao;
import com.mycompany.model.sys.SysRight;
import com.mycompany.model.sys.SysRole;
import com.mycompany.model.sys.SysUser;
import com.mycompany.service.sys.SysRoleManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liaoxiang on 2016/4/13.
 */

@Service
public class SysRoleManagerImpl extends GenericManagerImpl<SysRole, Long> implements SysRoleManager {

    SysRoleDao roleDao;

    @Autowired
    public SysRoleManagerImpl(SysRoleDao roleDao) {
        super(roleDao);
        this.roleDao = roleDao;
    }


    @Override
    public Pagination findListByCondition(QueryObject queryObject, SysRole sysRole) {
        Pagination page = roleDao.findListByCondition(queryObject, sysRole);
        return page;
    }

    public List<SysRole> findListDuplication(String dupName, Long id){
        return roleDao.findListDuplication(dupName, id);
    }
}
