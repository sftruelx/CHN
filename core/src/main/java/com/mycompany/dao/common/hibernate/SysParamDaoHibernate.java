package com.mycompany.dao.common.hibernate;

import com.mycompany.dao.sys.SysParamDao;
import com.mycompany.model.sys.SysParam;
import com.mycompany.model.sys.SysRole;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wy on 2016/6/17
 */
@Repository
public class SysParamDaoHibernate extends GenericDaoHibernate<SysParam, Long> implements SysParamDao {

    public SysParamDaoHibernate() {
        super(SysParam.class);
    }

    @Override
    public Pagination findListByCondition(QueryObject queryObject, SysParam sysParams) {
        int pageNo = 1;
        int pageSize = 10;

        Pagination pager = new Pagination();
        try {
            Criteria criteria = this.getSession().createCriteria(SysParam.class);

            if (sysParams != null) {
                if (sysParams.getName() != null && !"".equals(sysParams.getName())) {
                    criteria.add(Restrictions.like("name", "%" + sysParams.getName() + "%"));
                }
            }

            String count = criteria.setProjection(Projections.rowCount()).uniqueResult().toString();
            int rowCount = Integer.parseInt(count);
            criteria.setProjection(null);
            criteria.setFirstResult((pageNo - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            List result = criteria.list();
            pager.setTotal(rowCount);
            pager.setRows(result);
            return pager;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public List<SysParam> findParamByCode(String code) {
        Criteria criteria = this.getSession().createCriteria(SysParam.class);

        if (code != null && !"".equals(code)) {
            criteria.add(Restrictions.eq("code", code));
        }
        List<SysParam> result = criteria.list();
        return result;
    }

    @Override
    public List<SysParam> findParamByName(String name) {
        Criteria criteria = this.getSession().createCriteria(SysParam.class);

        if (name != null && !"".equals(name)) {
            criteria.add(Restrictions.eq("name", name));
        }
        List<SysParam> result = criteria.list();
        return result;
    }
}
