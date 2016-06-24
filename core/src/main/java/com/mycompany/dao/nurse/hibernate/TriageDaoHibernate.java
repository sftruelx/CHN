package com.mycompany.dao.nurse.hibernate;

import com.mycompany.dao.common.hibernate.GenericDaoHibernate;
import com.mycompany.dao.nurse.ITriageDao;
import com.mycompany.model.nurse.Triage;
import com.mycompany.util.DateUtils;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wsd on 2016/6/15.  分诊管理dao实现层
 */
@Repository
public class TriageDaoHibernate extends GenericDaoHibernate<Triage,Long> implements ITriageDao {

    public TriageDaoHibernate() {
        super(Triage.class);
    }

    // 根据分页条件获取列表
    @Override
    public Pagination findListDetail(QueryObject queryObject, String regStartDate,String regEndDate, String status) {
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
            Criteria criteria = this.getSession().createCriteria(Triage.class);
            criteria.add(Restrictions.between("regDate", DateUtils.formatStringDate(regStartDate.trim()), DateUtils.plusOneDay(DateUtils.formatStringDate(regEndDate.trim()))));
            if (status!=null&&status!=""){
                criteria.add(Restrictions.eq("status", Integer.parseInt(status.trim())));
            }
            String count = criteria.setProjection(Projections.rowCount()).uniqueResult().toString();
            int rowCount = Integer.parseInt(count);
            criteria.setProjection(null);
            criteria.setFirstResult((pageNo - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            if ("desc".equals(queryObject.getOrder())) {
                criteria.addOrder(Order.desc("id"));
            } else {
                criteria.addOrder(Order.asc("id"));
            }
            List result = criteria.list();
            pager.setTotal(rowCount);
            pager.setRows(result);
            return pager;
        } catch (Exception ex) {
            return null;
        }
    }

    //根据流水号查找分诊挂号记录
    @Override
    public Triage findTriageByNo(String triageNo) {
        Criteria criteria = this.getSession().createCriteria(Triage.class);
        criteria.add(Restrictions.eq("triageNo", triageNo));
        List<Triage> result = criteria.list();
        return result.size()>0?result.get(0):null;
    }

}
