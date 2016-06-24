package com.mycompany.service;

import com.mycompany.model.nurse.BizPatient;
import com.mycompany.model.sys.Trainning;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;


/**
 * Created by liaoxiang on 2016/4/13.
 */
public interface PatientManager extends GenericManager<BizPatient, Long>{

    Pagination findListByCondition(QueryObject queryObject,BizPatient bizPatient);

    BizPatient bindOpenID(String name, String mobile, String openID);

    BizPatient findByOpenID(String openID);

    Trainning findByPatientId(Long patientId);
}
