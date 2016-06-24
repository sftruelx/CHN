package com.mycompany.dao.common.hibernate;

import com.mycompany.dao.nurse.BizPatientHistoryDao;
import com.mycompany.model.nurse.BizPatientHistory;
import org.springframework.stereotype.Repository;

/**
 * Created by wuy on 2016/4/13.
 */

@Repository
public class BizPatientHistoryDaoHibernate extends GenericDaoHibernate<BizPatientHistory, Long> implements BizPatientHistoryDao {

    public BizPatientHistoryDaoHibernate() {
        super(BizPatientHistory.class);
    }

}
