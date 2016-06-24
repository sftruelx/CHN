package com.mycompany.service.impl;

import com.mycompany.dao.sys.SysUserDao;
import com.mycompany.model.sys.SysUser;
import com.mycompany.service.sys.SysUserManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserManagerImpl extends GenericManagerImpl<SysUser, Long> implements SysUserManager {
	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	public SysUserManagerImpl(SysUserDao sysUserDao) {
		super(sysUserDao);
		this.sysUserDao = sysUserDao;
	}


	@Override
//	@Cacheable(value="myCache",key="#root.targetClass + #root.methodName")
	public Pagination findListByCondition(QueryObject queryObject, SysUser sysUser) {
		Pagination page = sysUserDao.findListByCondition(queryObject, sysUser);
		return page;
	}

	public List<SysUser> findListDuplication(String usrName, Long id){
		return sysUserDao.findListDuplication(usrName, id);
	}

	@Override
//	@Cacheable(value = "myCache", key = "#username")
	public SysUser loadUserByUsername(String username){
		return sysUserDao.loadUserByUsername(username);
	}


	public SysUser save(SysUser sysUser){
		return sysUserDao.save(sysUser);
	}

}
