package com.mycompany.service.impl;

import com.mycompany.dao.sys.SysParamDao;
import com.mycompany.model.sys.SysParam;
import com.mycompany.service.sys.SysParamService;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wy on 2016/6/17
 */
@Service
public class SysParamServiceImpl extends GenericManagerImpl<SysParam, Long> implements SysParamService {

    @Autowired
    private SysParamDao sysParamDao;

    @Autowired
    public SysParamServiceImpl(SysParamDao sysParamDao) {
        super(sysParamDao);
        this.sysParamDao = sysParamDao;
    }

    @Override
    public Pagination findListByCondition(QueryObject queryObject, SysParam sysParam) {
        return sysParamDao.findListByCondition(queryObject, sysParam);
    }

    @Override
    public List<SysParam> findParamByCode(String code) {
        return sysParamDao.findParamByCode(code);
    }

    @Override
    public List<SysParam> findParamByName(String name) {
        return sysParamDao.findParamByName(name);
    }
}
