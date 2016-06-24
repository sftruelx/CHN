package com.mycompany.dao.common.hibernate;

import com.mycompany.dao.common.PatientDao;
import com.mycompany.model.nurse.BizPatient;
import com.mycompany.model.sys.Trainning;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liaoxiang on 2016/4/13.
 */

@Repository
public class PatientDaoHibernate extends GenericDaoHibernate<BizPatient, Long> implements PatientDao {

    public PatientDaoHibernate() {
        super(BizPatient.class);
    }

    public Pagination findListByCondition(QueryObject queryObject, BizPatient bizPatient) {

        int pageNo = 1;
        int pageSize = 10;
        if (queryObject.getOffset() >= 0 && queryObject.getLimit() > 0) {
            if (queryObject.getOffset() == 0) {
                pageNo = 1;
            } else {
                pageNo = queryObject.getOffset() / queryObject.getLimit() + 1;
            }
        }
        Pagination pager = new Pagination();
        try {
            Criteria criteria = this.getSession().createCriteria(BizPatient.class);

            if (bizPatient != null) {
                if (bizPatient.getName() != null && !"".equals(bizPatient.getName())) {
                    criteria.add(Restrictions.like("name", "%" + bizPatient.getName() + "%"));
                }
                if (bizPatient.getStatus() != null) {
                    criteria.add(Restrictions.eq("status", bizPatient.getStatus()));
                }
            }

            String count = criteria.setProjection(Projections.rowCount()).uniqueResult().toString();
            int rowCount = Integer.parseInt(count);
            criteria.setProjection(null);
            criteria.setFirstResult((pageNo - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            if ("desc".equals(queryObject.getOrder())) {
                criteria.addOrder(Order.desc("createDate"));
            } else {
                criteria.addOrder(Order.asc("createDate"));
            }
            List result = criteria.list();
            pager.setTotal(rowCount);
            pager.setRows(result);
            return pager;
        } catch (Exception ex) {
            return null;
        }
    }

    public BizPatient findByNameAndMobile(String name, String mobile) {
        Criteria criteria = this.getSession().createCriteria(BizPatient.class);
        criteria.add(Restrictions.eq("name", name.trim()));
        criteria.add(Restrictions.eq("mobile", mobile.trim()));
        List<BizPatient> result = criteria.list();
        return result.get(0);
    }

    @Override
    public BizPatient findByOpenId(String openId) {
        Criteria criteria = this.getSession().createCriteria(BizPatient.class);
        criteria.add(Restrictions.eq("openID", openId));
        List<BizPatient> result = criteria.list();
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public Trainning findByPatientId(Long patientId) {
        Criteria criteria = this.getSession().createCriteria(Trainning.class);
        criteria.add(Restrictions.eq("patient_id", patientId));
        List<Trainning> result = criteria.list();
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }
}
