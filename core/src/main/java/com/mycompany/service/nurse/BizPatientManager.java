package com.mycompany.service.nurse;

import com.mycompany.model.common.ActionMsg;
import com.mycompany.model.nurse.BizHisPatient;
import com.mycompany.model.nurse.BizPatient;
import com.mycompany.model.sys.Trainning;
import com.mycompany.service.GenericManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by liaoxiang on 2016/4/13.
 */
public interface BizPatientManager extends GenericManager<BizPatient, Long> {

    Pagination findListByCondition(QueryObject queryObject, BizPatient bizPatient);

    BizPatient bindOpenID(String name, String mobile, String openID);

    BizPatient findByOpenID(String openID);

    Trainning findByPatientId(Long patientId);

    BizPatient findOutpatientNo(String outpatientNo);

    BizHisPatient findHisNo(String outpatientNo);

    ActionMsg saveBizPatientRecord(HttpServletRequest request, BizPatient bizPatient);
}
