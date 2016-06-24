package com.mycompany.dao.common.hibernate;


import com.mycompany.dao.sys.SysLogDao;
import com.mycompany.model.sys.SysLog;
import com.mycompany.model.sys.SysLogName;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import com.mycompany.util.StringUtil;
import com.sun.xml.bind.v2.TODO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by liaoxiang on 2016/6/15.
 */
@Repository
public class SysLogDaoHibernate   extends GenericDaoHibernate<SysLog, Long> implements SysLogDao {

    public SysLogDaoHibernate() {
        super(SysLog.class);
    }

    public Pagination findListByCondition(QueryObject queryObject, SysLog sysLog) {
        int pageNo = 1;
        int pageSize = queryObject.getLimit();

        if (queryObject.getOffset() >= 0 && queryObject.getLimit() > 0) {
            if (queryObject.getOffset() == 0) {
                pageNo = 1;
            } else {
                pageNo = queryObject.getOffset() / queryObject.getLimit() + 1;
            }
        }
        Pagination pager = new Pagination();
        try {
            String queryStr = "";
            if (sysLog != null) {
                if (!StringUtils.isEmpty(sysLog.getUsername())) {
                    queryStr = queryStr + " and b.username like :username ";
                }
                if (!StringUtils.isEmpty(sysLog.getUrl())) {
                    queryStr = queryStr + " and bc.urlName like :url";
                }
                if (!StringUtils.isEmpty(sysLog.getIp())) {
                    queryStr = queryStr + " and b.ip = :ip";
                }
            }
            Query query = this.getSession().createQuery("select count(*) from SysLog b,SysLogName bc where b.url=bc.url " + queryStr);
            if (sysLog != null) {
                if (!StringUtils.isEmpty(sysLog.getUsername())) {
                    query.setString("username", "%" + sysLog.getUsername() + "%");
                }
                if (!StringUtils.isEmpty(sysLog.getUrl())) {
                    query.setString("url", "%" + sysLog.getUrl() + "%");
                }
                if (!StringUtils.isEmpty(sysLog.getIp())) {
                    query.setString("ip",  sysLog.getIp() );
                }
            }
            int rowCount = Integer.parseInt(query.uniqueResult().toString());

            String hql = "select new com.mycompany.model.sys.SysLog(b.id, bc.urlName, b.ip, b.username, b.createTime, b.role, b.userId)" +
                    " from SysLog b,SysLogName bc where b.url=bc.url";

            query = this.getSession().createQuery(hql+queryStr);
            if (sysLog != null) {
                if (!StringUtils.isEmpty(sysLog.getUsername())) {
                   query.setString("username", "%" + sysLog.getUsername() + "%");
                }
                if (!StringUtils.isEmpty(sysLog.getUrl())) {
                  query.setString("url", "%" + sysLog.getUrl() + "%");
                }
                if (!StringUtils.isEmpty(sysLog.getIp())) {
                    query.setString("ip",  sysLog.getIp() );
                }
            }
            query.setMaxResults(pageSize);
            query.setFirstResult((pageNo-1)*pageSize);
            List<SysLog> result = (List<SysLog>)query.list();
            pager.setTotal(rowCount);
            pager.setRows(result);
            return pager;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
