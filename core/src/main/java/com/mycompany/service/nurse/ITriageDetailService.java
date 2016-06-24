package com.mycompany.service.nurse;

import com.mycompany.model.nurse.Triage;
import com.mycompany.model.nurse.TriageDetail;
import com.mycompany.service.GenericManager;

/**
 * Created by wsd on 2016/6/15.   检查项目服务层接口
 */
public interface ITriageDetailService extends GenericManager<TriageDetail,Long>{


    //根据流水号把明细表删除
    void deleteByTriageNo(String triageNo);

}
