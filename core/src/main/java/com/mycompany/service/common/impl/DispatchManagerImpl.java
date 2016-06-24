package com.mycompany.service.common.impl;

import com.mycompany.dao.common.DispatchDao;
import com.mycompany.dao.sys.RoleCopyDao;
import com.mycompany.model.common.Dispatch;
import com.mycompany.model.sys.RoleCopy;
import com.mycompany.service.common.DispatchManager;
import com.mycompany.service.impl.GenericManagerImpl;
import com.mycompany.service.sys.RoleCopyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liaoxiang on 2016/4/13.
 */

@Service
public class DispatchManagerImpl extends GenericManagerImpl<Dispatch, Long> implements DispatchManager {

    DispatchDao dispatchDao;

    @Autowired
    public DispatchManagerImpl(DispatchDao dispatchDao) {
        super(dispatchDao);
        this.dispatchDao = dispatchDao;
    }

}
