package com.mycompany.dao.sys;

import com.mycompany.dao.common.GenericDao;
import com.mycompany.model.sys.SysParam;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

import java.util.List;

/**
 * Created by wy on 2016/6/17
 */
public interface SysParamDao extends GenericDao<SysParam,Long> {

    Pagination findListByCondition(QueryObject queryObject, SysParam sysParams);

    List<SysParam> findParamByName(String name);

    List<SysParam> findParamByCode(String code);

}
