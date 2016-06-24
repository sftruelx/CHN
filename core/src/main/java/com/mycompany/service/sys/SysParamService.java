package com.mycompany.service.sys;

import com.mycompany.model.sys.SysParam;
import com.mycompany.service.GenericManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

import java.util.List;

/**
 * Created by wy on 2016/6/17
 */
public interface SysParamService extends GenericManager<SysParam, Long> {

    List<SysParam> findParamByName(String name);

    List<SysParam> findParamByCode(String code);

    Pagination findListByCondition(QueryObject queryObject, SysParam sysParams);
}
