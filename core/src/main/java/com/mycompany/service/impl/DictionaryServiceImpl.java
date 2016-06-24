package com.mycompany.service.impl;

import com.mycompany.dao.sys.IDictionaryDao;
import com.mycompany.model.sys.Dictionary;
import com.mycompany.service.sys.IDictionaryService;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wsd on 2016/5/30.   字典管理服务层实现
 */
@Service
public class DictionaryServiceImpl extends GenericManagerImpl<Dictionary,Long> implements IDictionaryService{

    @Autowired
    private IDictionaryDao dictionaryDao;

    @Autowired
    public DictionaryServiceImpl(IDictionaryDao dictionaryDao) {
        super(dictionaryDao);
        this.dictionaryDao = dictionaryDao;
    }

    // 根据字典类型dict_type获取字典  wsd on 2016/5/30
    @Override
    public List<Dictionary> findDictByType(Integer type) {
        return dictionaryDao.findDictByType(type);
    }

    // 根据字典dict_parent获取子字典  wsd on 2016/5/30
    @Override
    public List<Dictionary> findDictByParent(String parentCode) {
        return dictionaryDao.findDictByParent(parentCode);
    }

    // 根据字典代码和类型获取字典  wsd on 2016/5/30
    @Override
    public Dictionary findDictByKeyAndTypeAndParent(String dict_key, Integer dict_type,String dict_parent) {
        return dictionaryDao.findDictByKeyAndTypeAndParent(dict_key,dict_type,dict_parent);
    }

    // 根据字典dict_parent和分页条件获取子字典  wsd on 2016/6/3
    @Override
    public Pagination findDictByParentAndQuery(QueryObject query, String dict_parent) {
        return dictionaryDao.findDictByParentAndQuery(query,dict_parent);
    }

    // 根据字典名称和类型和主字典获取字典  wsd on 2016/6/17
    @Override
    public Dictionary findDictByValueAndTypeAndParent(String checkValue, Integer dict_type, String dict_parent) {
        return dictionaryDao.findDictByValueAndTypeAndParent(checkValue,dict_type,dict_parent);
    }
}
