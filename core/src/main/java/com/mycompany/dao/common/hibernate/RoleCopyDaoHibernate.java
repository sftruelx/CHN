package com.mycompany.dao.common.hibernate;

import com.mycompany.dao.sys.RoleCopyDao;
import com.mycompany.model.sys.RoleCopy;
import org.springframework.stereotype.Repository;

/**
 * Created by liaoxiang on 2016/4/13.
 */

@Repository
public class RoleCopyDaoHibernate extends GenericDaoHibernate<RoleCopy, Long> implements RoleCopyDao {

    public RoleCopyDaoHibernate() {
        super(RoleCopy.class);
    }

}
