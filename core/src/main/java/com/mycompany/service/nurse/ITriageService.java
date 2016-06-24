package com.mycompany.service.nurse;

import com.mycompany.model.nurse.Triage;
import com.mycompany.service.GenericManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

/**
 * Created by wsd on 2016/6/15.   检查项目服务层接口
 */
public interface ITriageService extends GenericManager<Triage,Long>{


    // 根据分页条件获取列表
    Pagination findListDetail(QueryObject query, String regStartDate,String regEndDate, String status);


    //根据流水号查找分诊挂号记录
    Triage findTriageByNo(String triageNo);

}
