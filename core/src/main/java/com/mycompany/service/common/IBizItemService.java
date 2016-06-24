package com.mycompany.service.common;

import com.mycompany.model.common.BizItem;
import com.mycompany.service.GenericManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

/**
 * Created by wsd on 2016/6/15.   检查项目服务层接口
 */
public interface IBizItemService extends GenericManager<BizItem,Long>{

    // 根据分页条件获取项目列表  wsd on 2016/6/17
    Pagination findBizItem(QueryObject query,String itemValue);

    // 根据itemCode获取项目  wsd on 2016/6/17
    BizItem findBizItemByCode(String itemCode);

    // 根据itemName获取项目  wsd on 2016/6/17
    BizItem findBizItemByName(String itemName);

}
