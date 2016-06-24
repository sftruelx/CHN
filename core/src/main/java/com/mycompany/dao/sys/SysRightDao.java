package com.mycompany.dao.sys;

import com.mycompany.dao.common.GenericDao;
import com.mycompany.model.sys.SysRight;
import com.mycompany.model.sys.SysUser;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

import java.util.List;

/**
 * Created by liaoxiang on 2016/4/7.
 */
public interface SysRightDao  extends GenericDao<SysRight, Long> {
    List<SysRight> getRights(SysRight sysRight);
    Pagination findListByCondition(QueryObject queryObject, SysRight sysRight);
    List<SysRight> findListDuplication(String dupName, Long id);
}
