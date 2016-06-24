package com.mycompany.dao.nurse;

import com.mycompany.dao.common.GenericDao;
import com.mycompany.model.nurse.BizHisPatient;
import com.mycompany.model.nurse.BizPatient;
import com.mycompany.model.sys.Trainning;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;


/**
 * Created by liaoxiang on 2016/4/13.
 */
public interface BizPatientDao extends GenericDao<BizPatient, Long> {

    Pagination findListByCondition(QueryObject queryObject, BizPatient bizPatient);

    BizPatient findByNameAndMobile(String name, String mobile);

    BizPatient findByOpenId(String openId);

    Trainning findByPatientId(Long patientId);

    BizPatient findOutpatientNo(String outpatientNo);

    BizPatient findCardId(String cardId);

    BizPatient findMedicareCard(String medicareCard);

    BizHisPatient findHisNo(String outpatientNo);
}
