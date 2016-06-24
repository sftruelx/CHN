package com.mycompany.dao.common.hibernate;

import com.mycompany.dao.sys.IDictionaryDao;
import com.mycompany.model.sys.Dictionary;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wsd on 2016/5/30.  字典管理dao实现层
 */
@Repository
public class DictionaryDaoHibernate extends GenericDaoHibernate<Dictionary,Long> implements IDictionaryDao{

    public DictionaryDaoHibernate() {
        super(Dictionary.class);
    }

    // 根据字典类型dict_type获取字典  wsd on 2016/5/30
    @Override
    public List<Dictionary> findDictByType(Integer type) {
        Criteria criteria = this.getSession().createCriteria(Dictionary.class);
        criteria.add(Restrictions.eq("dict_type", type));
        List<Dictionary> result = criteria.list();
        return result;
    }

    // 根据字典dict_parent获取子字典  wsd on 2016/5/30
    @Override
    public List<Dictionary> findDictByParent(String parentCode) {
        Criteria criteria = this.getSession().createCriteria(Dictionary.class);
        criteria.add(Restrictions.eq("dict_type", 1));
        criteria.add(Restrictions.eq("dict_parent", parentCode));
        List<Dictionary> result = criteria.list();
        return result;
    }

    // 根据字典代码和类型获取字典  wsd on 2016/5/30
    @Override
    public Dictionary findDictByKeyAndTypeAndParent(String dict_key, Integer dict_type,String dict_parent) {
        Criteria criteria = this.getSession().createCriteria(Dictionary.class);
        criteria.add(Restrictions.eq("dict_type", dict_type));
        criteria.add(Restrictions.eq("dict_key", dict_key));
        if (dict_parent!=null&&dict_parent!=""){
            criteria.add(Restrictions.eq("dict_parent", dict_parent));
        }
        List<Dictionary> result = criteria.list();
        return result.size()>0?result.get(0):null;
    }

    // 根据字典名称和类型和主字典获取字典  wsd on 2016/6/17
    @Override
    public Dictionary findDictByValueAndTypeAndParent(String checkValue, Integer dict_type, String dict_parent) {
        Criteria criteria = this.getSession().createCriteria(Dictionary.class);
        criteria.add(Restrictions.eq("dict_type", dict_type));
        criteria.add(Restrictions.eq("dict_value", checkValue));
        if (dict_parent!=null&&dict_parent!=""){
            criteria.add(Restrictions.eq("dict_parent", dict_parent));
        }
        List<Dictionary> result = criteria.list();
        return result.size()>0?result.get(0):null;
    }

    // 根据字典dict_parent和分页条件获取子字典  wsd on 2016/6/3
    @Override
    public Pagination findDictByParentAndQuery(QueryObject queryObject, String dict_parent) {
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
            Criteria criteria = this.getSession().createCriteria(Dictionary.class);
            criteria.add(Restrictions.eq("dict_parent",dict_parent));
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
}
