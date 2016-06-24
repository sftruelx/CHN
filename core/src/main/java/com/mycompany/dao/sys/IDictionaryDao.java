package com.mycompany.dao.sys;

import com.mycompany.dao.common.GenericDao;
import com.mycompany.model.sys.Dictionary;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

import java.util.List;

/**
 * Created by wsd on 2016/5/30. 字典管理dao接口层
 */
public interface IDictionaryDao extends GenericDao<Dictionary,Long> {

    // 根据字典类型dict_type获取字典  wsd on 2016/5/30
    List<Dictionary> findDictByType(Integer type);

    // 根据字典dict_parent获取子字典  wsd on 2016/5/30
    List<Dictionary> findDictByParent(String parentCode);

    // 根据字典代码和类型获取字典  wsd on 2016/5/30
    Dictionary findDictByKeyAndTypeAndParent(String dict_key, Integer dict_type,String dict_parent);

    // 根据字典dict_parent和分页条件获取子字典  wsd on 2016/6/3
    Pagination findDictByParentAndQuery(QueryObject query, String dict_parent);

    // 根据字典名称和类型和主字典获取字典  wsd on 2016/6/17
    Dictionary findDictByValueAndTypeAndParent(String checkValue, Integer dict_type, String dict_parent);
}
