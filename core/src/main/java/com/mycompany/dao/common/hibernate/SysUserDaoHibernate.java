package com.mycompany.dao.common.hibernate;


import com.mycompany.dao.sys.SysUserDao;
import com.mycompany.model.sys.SysUser;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SysUserDaoHibernate extends GenericDaoHibernate<SysUser , Long> implements SysUserDao {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public SysUserDaoHibernate() {
		super(SysUser.class);
	}
	
	public Pagination findListByCondition(QueryObject queryObject, SysUser sysUser) {

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
			Criteria criteria = this.getSession().createCriteria(SysUser.class);

			if (sysUser != null) {
				if (sysUser.getUsername() != null && !"".equals(sysUser.getUsername())) {
					criteria.add(Restrictions.like("usrName", "%" + sysUser.getUsername() + "%"));
				}
				if (sysUser.getName() != null && !"".equals(sysUser.getName())) {
					criteria.add(Restrictions.like("name", "%" + sysUser.getName() + "%"));
				}
				if (sysUser.getUsrType() != null) {
					criteria.add(Restrictions.eq("status", sysUser.getUsrType()));
				}
				if (sysUser.getUsrDepartment() != null) {
					criteria.add(Restrictions.eq("department", sysUser.getUsrDepartment()));
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

	public List<SysUser> findListDuplication(String usrname, Long id) throws UsernameNotFoundException {
		logger.info("load user by username ........");
//		List users = getSession().createCriteria(SysUser.class)
		Criteria criteria = this.getSession().createCriteria(SysUser.class);
		if(usrname != null){
			criteria.add(Restrictions.eq("usrName", usrname));
		}
		if(id != null) {
			criteria.add(Restrictions.ne("id", id));
		}
		List users = criteria.list();

		return users;

	}

	public SysUser loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("load user by username ........");
		Criteria criteria = this.getSession().createCriteria(SysUser.class);
		criteria.add(Restrictions.eq("usrName", username));
		criteria.add(Restrictions.eq("usrFlag", 1));
		List users = criteria.list();


		if (users == null || users.isEmpty()) {
			throw new UsernameNotFoundException("user '" + username + "' not found...");
		} else {
			return (SysUser) users.get(0);
		}
	}
}
