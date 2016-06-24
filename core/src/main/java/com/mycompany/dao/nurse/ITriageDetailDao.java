package com.mycompany.dao.nurse;

import com.mycompany.dao.common.GenericDao;
import com.mycompany.model.nurse.TriageDetail;

/**
 * Created by wsd on 2016/5/30. 分诊管理明细dao接口层
 */
public interface ITriageDetailDao extends GenericDao<TriageDetail,Long> {

    //根据流水号把明细表删除
    void deleteByTriageNo(String triageNo);
}
