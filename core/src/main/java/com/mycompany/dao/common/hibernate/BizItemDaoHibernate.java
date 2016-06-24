package com.mycompany.dao.common.hibernate;

import com.mycompany.dao.common.IBizItemDao;
import com.mycompany.model.common.BizItem;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wsd on 2016/6/15.  检查项目dao实现层
 */
@Repository
public class BizItemDaoHibernate extends GenericDaoHibernate<BizItem,Long> implements IBizItemDao {

    public BizItemDaoHibernate() {
        super(BizItem.class);
    }

    // 根据分页条件获取项目列表  wsd on 2016/6/17
    @Override
    public Pagination findBizItem(QueryObject queryObject,String itemValue) {
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
            Criteria criteria = this.getSession().createCriteria(BizItem.class);
            if (itemValue!=null&&itemValue!=""){
                Disjunction dis=Restrictions.disjunction();
                dis.add(Restrictions.like("itemCode","%"+ itemValue+"%"));
                dis.add(Restrictions.like("itemName","%"+ itemValue+"%"));
                criteria.add(dis);
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

    // 根据itemCode获取项目  wsd on 2016/6/17
    @Override
    public BizItem findBizItemByCode(String itemCode) {
        Criteria criteria = this.getSession().createCriteria(BizItem.class);
        criteria.add(Restrictions.eq("itemCode", itemCode));
        List<BizItem> result = criteria.list();
        return result.size()>0?result.get(0):null;
    }

    // 根据itemName获取项目  wsd on 2016/6/17
    @Override
    public BizItem findBizItemByName(String itemName) {
        Criteria criteria = this.getSession().createCriteria(BizItem.class);
        criteria.add(Restrictions.eq("itemName", itemName));
        List<BizItem> result = criteria.list();
        return result.size()>0?result.get(0):null;
    }
}
