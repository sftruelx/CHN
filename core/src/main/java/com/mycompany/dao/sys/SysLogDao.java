package com.mycompany.dao.sys;

import com.mycompany.dao.common.GenericDao;
import com.mycompany.model.sys.SysLog;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

/**
 * Created by liaoxiang on 2016/6/15.
 */
public interface SysLogDao   extends GenericDao<SysLog, Long> {
    Pagination findListByCondition(QueryObject queryObject, SysLog sysLog);
}
