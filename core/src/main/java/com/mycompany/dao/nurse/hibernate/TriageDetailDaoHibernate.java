package com.mycompany.dao.nurse.hibernate;

import com.mycompany.dao.common.hibernate.GenericDaoHibernate;
import com.mycompany.dao.nurse.ITriageDetailDao;
import com.mycompany.model.nurse.TriageDetail;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wsd on 2016/6/15.  分诊管理明细dao实现层
 */
@Repository
public class TriageDetailDaoHibernate extends GenericDaoHibernate<TriageDetail,Long> implements ITriageDetailDao {


    public TriageDetailDaoHibernate() {
        super(TriageDetail.class);
    }

    //根据流水号把明细表删除
    @Override
    public void deleteByTriageNo(String triageNo) {
        Criteria criteria = this.getSession().createCriteria(TriageDetail.class);
        criteria.add(Restrictions.eq("triageNo", triageNo));
        List<TriageDetail> result = criteria.list();
        if (result.size()>0){
            for (TriageDetail triageDetail:result){
                this.remove(triageDetail);
            }
        }

    }
}
