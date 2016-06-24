package com.mycompany.service.sys;

import com.mycompany.model.sys.SysLog;
import com.mycompany.service.GenericManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

/**
 * Created by liaoxiang on 2016/6/15.
 */
public interface SysLogManager  extends GenericManager<SysLog, Long> {

    Pagination findListByCondition(QueryObject queryObject, SysLog sysLog);
}
