package com.mycompany.service.sys;

import com.mycompany.model.sys.Dictionary;
import com.mycompany.service.GenericManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

import java.util.List;

/**
 * Created by wsd on 2016/5/30.   字典管理服务层接口
 */
public interface IDictionaryService extends GenericManager<Dictionary,Long>{

    // 根据字典类型dict_type获取字典  wsd on 2016/5/30
    public List<Dictionary> findDictByType(Integer type);

    // 根据字典dict_parent获取子字典  wsd on 2016/5/30
    public List<Dictionary> findDictByParent(String parentCode);

    // 根据字典代码和类型和主字典获取字典  wsd on 2016/6/2
    public Dictionary findDictByKeyAndTypeAndParent(String dict_key,Integer dict_type,String dict_parent);

    // 根据字典名称和类型和主字典获取字典  wsd on 2016/6/17
    public Dictionary findDictByValueAndTypeAndParent(String checkValue,Integer dict_type,String dict_parent);

    // 根据字典dict_parent和分页条件获取子字典  wsd on 2016/6/3
    Pagination findDictByParentAndQuery(QueryObject query, String dict_parent);

}
