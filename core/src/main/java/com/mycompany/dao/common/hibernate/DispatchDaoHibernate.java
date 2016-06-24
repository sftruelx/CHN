package com.mycompany.dao.common.hibernate;


import com.mycompany.dao.common.DispatchDao;
import com.mycompany.model.common.Dispatch;
import org.springframework.stereotype.Repository;

/**
 * Created by liaoxiang on 2016/5/25.
 */
@Repository
public class DispatchDaoHibernate extends GenericDaoHibernate<Dispatch, Long> implements DispatchDao {

    public DispatchDaoHibernate() {
        super(Dispatch.class);
    }

}
