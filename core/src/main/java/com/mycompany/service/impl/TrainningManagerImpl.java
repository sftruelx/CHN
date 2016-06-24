package com.mycompany.service.impl;

import com.mycompany.dao.sys.TrainningDao;
import com.mycompany.model.sys.Trainning;
import com.mycompany.service.sys.TrainningManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liaoxiang on 2016/4/13.
 */

@Service
public class TrainningManagerImpl extends GenericManagerImpl<Trainning, Long> implements TrainningManager {

    TrainningDao trainningDao;

    @Autowired
    public TrainningManagerImpl(TrainningDao trainningDao) {
        super(trainningDao);
        this.trainningDao = trainningDao;
    }

}
