package com.mycompany.dao.common;

import com.mycompany.model.common.BizItem;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

import java.util.List;

/**
 * Created by wsd on 2016/6/15. 检查项目dao接口层
 */
public interface IBizItemDao extends GenericDao<BizItem,Long> {


    // 根据分页条件获取项目列表  wsd on 2016/6/17
    Pagination findBizItem(QueryObject query,String itemValue);

    // 根据itemCode获取项目  wsd on 2016/6/17
    BizItem findBizItemByCode(String itemCode);

    // 根据itemName获取项目  wsd on 2016/6/17
    BizItem findBizItemByName(String itemName);


}
