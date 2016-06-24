package com.mycompany.service.nurse.impl;

import com.mycompany.dao.nurse.ITriageDao;
import com.mycompany.model.nurse.Triage;
import com.mycompany.service.impl.GenericManagerImpl;
import com.mycompany.service.nurse.ITriageService;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wsd on 2016/6/15.   检查项目服务层实现
 */
@Service
public class TriageServiceImpl extends GenericManagerImpl<Triage,Long> implements ITriageService{

    @Autowired
    private ITriageDao triageDao;

    @Autowired
    public TriageServiceImpl(ITriageDao triageDao) {
        super(triageDao);
        this.triageDao = triageDao;
    }

    // 根据分页条件获取列表
    @Override
    public Pagination findListDetail(QueryObject query, String regStartDate,String regEndDate, String status) {
        return triageDao.findListDetail(query,regStartDate,regEndDate,status);
    }

    //根据流水号查找分诊挂号记录
    @Override
    public Triage findTriageByNo(String triageNo) {
        return triageDao.findTriageByNo(triageNo);
    }
}
