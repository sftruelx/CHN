package com.mycompany.service.common.impl;

import com.mycompany.dao.common.IBizItemDao;
import com.mycompany.model.common.BizItem;
import com.mycompany.service.common.IBizItemService;
import com.mycompany.service.impl.GenericManagerImpl;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wsd on 2016/6/15.   检查项目服务层实现
 */
@Service
public class BizItemServiceImpl extends GenericManagerImpl<BizItem,Long> implements IBizItemService{

    @Autowired
    private IBizItemDao bizItemDao;

    @Autowired
    public BizItemServiceImpl(IBizItemDao bizItemDao) {
        super(bizItemDao);
        this.bizItemDao = bizItemDao;
    }

    // 根据分页条件获取项目列表  wsd on 2016/6/17
    @Override
    public Pagination findBizItem(QueryObject query,String itemValue) {
        return bizItemDao.findBizItem(query,itemValue);
    }

    // 根据itemCode获取项目  wsd on 2016/6/17
    @Override
    public BizItem findBizItemByCode(String itemCode) {
        return bizItemDao.findBizItemByCode(itemCode);
    }

    // 根据itemName获取项目  wsd on 2016/6/17
    @Override
    public BizItem findBizItemByName(String itemName) {
        return bizItemDao.findBizItemByName(itemName);
    }
}
