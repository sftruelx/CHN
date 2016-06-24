package com.mycompany.service.impl;


import com.mycompany.dao.sys.SysRightDao;
import com.mycompany.model.sys.SysRight;
import com.mycompany.model.sys.SysUser;
import com.mycompany.service.sys.SysRightManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("SysRightService")
public class SysRightManagerImpl extends GenericManagerImpl<SysRight, Long>  implements
		SysRightManager {

	private SysRightDao sysRightDao;


	@Autowired
	public SysRightManagerImpl(SysRightDao sysRightDao) {
		super(sysRightDao);
		this.sysRightDao = sysRightDao;
	}
	@Override
	public List<SysRight> loadRights(SysRight SysRight) {
		List<SysRight> resultList = this.sysRightDao.getRights(SysRight);

		return resultList;
	}


	
	@Override
	public List<SysRight> loadRightsByRole(SysRight SysRight) {
		List<SysRight> resultList = this.sysRightDao.getRights(SysRight);

		return resultList;
	}

	@Override
	public Pagination findListByCondition(QueryObject queryObject, SysRight sysRight) {
		Pagination page = sysRightDao.findListByCondition(queryObject, sysRight);
		return page;
	}

	public List<SysRight> findListDuplication(String dupName, Long id){
		return sysRightDao.findListDuplication(dupName, id);
	}
}
