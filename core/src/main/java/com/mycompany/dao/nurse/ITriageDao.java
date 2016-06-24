package com.mycompany.dao.nurse;

import com.mycompany.dao.common.GenericDao;
import com.mycompany.model.nurse.Triage;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

/**
 * Created by wsd on 2016/5/30. 分诊管理dao接口层
 */
public interface ITriageDao extends GenericDao<Triage,Long> {


    // 根据分页条件获取列表
    Pagination findListDetail(QueryObject query,String regStartDate,String regEndDate, String status);

    //根据流水号查找分诊挂号记录
    Triage findTriageByNo(String triageNo);
}
