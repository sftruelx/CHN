package com.mycompany.dao.common.hibernate;

import java.util.List;
import com.mycompany.dao.sys.SysRightDao;
import com.mycompany.model.sys.SysRight;
import com.mycompany.model.sys.SysRole;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class SysRightDaoHibernate extends GenericDaoHibernate<SysRight, Long> implements SysRightDao {

    public SysRightDaoHibernate() {
        super(SysRight.class);
    }

    @Override
    public List<SysRight> getRights(final SysRight sysRight) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT sysRight FROM SysRight sysRight");
        if (null != sysRight.getParentId()) {
            sb.append(" WHERE sysRight.parent.id = :parentId");
        } else {
            sb.append(" WHERE sysRight.parent IS NULL");
        }
        sb.append(" ORDER BY sysRight.lastOperatorTime DESC");
        Query query = getSession().createQuery(sb.toString());
        if (null != sysRight.getParentId()) {
            query.setParameter("parentId", sysRight.getParentId());
        }

        return query.list();
    }


    public Pagination findListByCondition(QueryObject queryObject, SysRight sysRight) {

        int pageNo = 1;
        int pageSize = 10;
        int limit = queryObject.getLimit();
        if (limit > 10) {
            pageSize = limit;
        }
        if (queryObject.getOffset() >= 0 && queryObject.getLimit() > 0) {
            if (queryObject.getOffset() == 0) {
                pageNo = 1;
            } else {
                pageNo = queryObject.getOffset() / queryObject.getLimit() + 1;
            }
        }
        Pagination pager = new Pagination();
        try {
            Criteria criteria = this.getSession().createCriteria(SysRight.class);

            if (sysRight != null) {
                if (sysRight.getRightText() != null && !"".equals(sysRight.getRightText())) {
                    criteria.add(Restrictions.like("rightText", "%" + sysRight.getRightText() + "%"));
                }
                if (sysRight.getRightUrl() != null && !"".equals(sysRight.getRightUrl())) {
                    criteria.add(Restrictions.like("rightUrl", "%" + sysRight.getRightUrl() + "%"));
                }
                if (sysRight.getParentId() != null && !"".equals(sysRight.getParentId())) {
                    criteria.add(Restrictions.eq("parent_id", sysRight.getParentId() ));
                }
            }

            String count = criteria.setProjection(Projections.rowCount()).uniqueResult().toString();
            int rowCount = Integer.parseInt(count);
            criteria.setProjection(null);
            criteria.setFirstResult((pageNo - 1) * pageSize);
            criteria.setMaxResults(pageSize);
			if ("desc".equals(queryObject.getOrder())) {
				criteria.addOrder(Order.desc(queryObject.getSort()));
			} else {
				criteria.addOrder(Order.asc(queryObject.getSort()));
			}
            List result = criteria.list();
            pager.setTotal(rowCount);
            pager.setRows(result);
            return pager;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public List<SysRight> findListDuplication(String dupName, Long id) throws UsernameNotFoundException {
        Criteria criteria = this.getSession().createCriteria(SysRight.class);
        if(dupName != null){
            criteria.add(Restrictions.eq("rightText", dupName));
        }
        if(id != null) {
            criteria.add(Restrictions.ne("id", id));
        }
        List rights = criteria.list();

        return rights;

    }
}
