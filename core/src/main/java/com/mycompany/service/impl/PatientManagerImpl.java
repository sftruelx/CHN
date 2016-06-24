package com.mycompany.service.impl;

import com.mycompany.dao.common.PatientDao;
import com.mycompany.model.nurse.BizPatient;
import com.mycompany.model.sys.Trainning;
import com.mycompany.service.PatientManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liaoxiang on 2016/4/13.
 */

@Service
public class PatientManagerImpl extends GenericManagerImpl<BizPatient, Long> implements PatientManager {

    private PatientDao patientDao;

    @Autowired
    public PatientManagerImpl(PatientDao patientDao) {
        super(patientDao);
        this.patientDao = patientDao;
    }

    @Override
    public Pagination findListByCondition(QueryObject queryObject, BizPatient bizPatient) {
        Pagination page = patientDao.findListByCondition(queryObject, bizPatient);
        return page;
    }

    public BizPatient bindOpenID(String name, String mobile, String openID) {
        BizPatient bizPatient = patientDao.findByNameAndMobile(name, mobile);
        bizPatient.setOpenid(openID);
        return patientDao.save(bizPatient);
    }

    public BizPatient findByOpenID(String openID) {
        return patientDao.findByOpenId(openID);
    }

    @Override
    public Trainning findByPatientId(Long patientId) {
        return patientDao.findByPatientId(patientId);
    }
}
