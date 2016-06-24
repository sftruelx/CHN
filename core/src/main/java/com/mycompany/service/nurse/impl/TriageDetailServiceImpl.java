package com.mycompany.service.nurse.impl;

import com.mycompany.dao.nurse.ITriageDetailDao;
import com.mycompany.model.nurse.TriageDetail;
import com.mycompany.service.impl.GenericManagerImpl;
import com.mycompany.service.nurse.ITriageDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wsd on 2016/6/15.   检查项目明细服务层实现
 */
@Service
public class TriageDetailServiceImpl extends GenericManagerImpl<TriageDetail,Long> implements ITriageDetailService{

    @Autowired
    private ITriageDetailDao triageDetailDao;

    @Autowired
    public TriageDetailServiceImpl(ITriageDetailDao triageDetailDao) {
        super(triageDetailDao);
        this.triageDetailDao = triageDetailDao;
    }

    //根据流水号把明细表删除
    @Override
    public void deleteByTriageNo(String triageNo) {
        triageDetailDao.deleteByTriageNo(triageNo);
    }
}
