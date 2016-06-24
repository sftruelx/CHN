package com.mycompany.service.impl;

import com.mycompany.dao.sys.SysLogDao;
import com.mycompany.model.sys.SysLog;
import com.mycompany.model.sys.SysRole;
import com.mycompany.service.sys.SysLogManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liaoxiang on 2016/6/15.
 */
@Service
public class SysLogManagerImpl  extends GenericManagerImpl<SysLog, Long>  implements
        SysLogManager {

    @Autowired
    private SysLogDao sysLogDao;
    @Autowired
    public SysLogManagerImpl(SysLogDao sysLogDao) {
        super(sysLogDao);
        this.sysLogDao = sysLogDao;
    }

    @Override
    public Pagination findListByCondition(QueryObject queryObject, SysLog sysLog) {
        Pagination page = sysLogDao.findListByCondition(queryObject, sysLog);
        return page;
    }

}
