package com.mycompany.dao.common.hibernate;

import com.mycompany.dao.sys.TrainningDao;
import com.mycompany.model.sys.Trainning;
import org.springframework.stereotype.Repository;

/**
 * Created by liaoxiang on 2016/4/13.
 */

@Repository
public class TrainningDaoHibernate extends GenericDaoHibernate<Trainning, Long> implements TrainningDao {

    public TrainningDaoHibernate() {
        super(Trainning.class);
    }

}
