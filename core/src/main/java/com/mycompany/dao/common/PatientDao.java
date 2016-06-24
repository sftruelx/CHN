package com.mycompany.dao.common;

import com.mycompany.model.nurse.BizPatient;
import com.mycompany.model.sys.Trainning;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;


/**
 * Created by liaoxiang on 2016/4/13.
 */
public interface PatientDao extends GenericDao<BizPatient, Long> {

    Pagination findListByCondition(QueryObject queryObject, BizPatient bizPatient);

    BizPatient findByNameAndMobile(String name, String mobile);

    BizPatient findByOpenId(String openId);

    Trainning findByPatientId(Long patientId);
}
