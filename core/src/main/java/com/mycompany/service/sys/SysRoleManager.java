package com.mycompany.service.sys;


import com.mycompany.model.sys.SysRole;
import com.mycompany.service.GenericManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

import java.util.List;

/**
 * Created by liaoxiang on 2016/4/13.
 */
public interface SysRoleManager extends GenericManager<SysRole, Long> {

    Pagination findListByCondition(QueryObject queryObject, SysRole sysRole);

    List<SysRole> findListDuplication(String dupName, Long id);

}
