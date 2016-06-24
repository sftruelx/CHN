package com.mycompany.dao.common.hibernate;

import com.mycompany.dao.sys.SysRoleDao;
import com.mycompany.model.sys.SysRole;
import com.mycompany.model.sys.SysUser;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysRoleDaoHibernate extends GenericDaoHibernate<SysRole, Long> implements SysRoleDao {

    public SysRoleDaoHibernate() {
        super(SysRole.class);
    }

    public Pagination findListByCondition(QueryObject queryObject, SysRole sysRole) {

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
            Criteria criteria = this.getSession().createCriteria(SysRole.class);

            if (sysRole != null) {
                if (sysRole.getRoleName() != null && !"".equals(sysRole.getRoleName())) {
                    criteria.add(Restrictions.like("roleName", "%" + sysRole.getRoleName() + "%"));
                }

            }

            String count = criteria.setProjection(Projections.rowCount()).uniqueResult().toString();
            int rowCount = Integer.parseInt(count);
            criteria.setProjection(null);
            criteria.setFirstResult((pageNo - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            /*if ("desc".equals(queryObject.getOrder())) {
                criteria.addOrder(Order.desc("createDate"));
			} else {
				criteria.addOrder(Order.asc("createDate"));
			}*/
            List result = criteria.list();
            pager.setTotal(rowCount);
            pager.setRows(result);
            return pager;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<SysRole> findListDuplication(String dupName, Long id) throws UsernameNotFoundException {
        Criteria criteria = this.getSession().createCriteria(SysRole.class);
        if(dupName != null){
            criteria.add(Restrictions.eq("roleName", dupName));
        }
        if(id != null) {
            criteria.add(Restrictions.ne("id", id));
        }
        List roles = criteria.list();

        return roles;

    }


}
