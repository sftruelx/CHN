package com.mycompany.service.sys;

import com.mycompany.model.sys.SysUser;
import com.mycompany.service.GenericManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

import java.util.List;

public interface SysUserManager extends GenericManager<SysUser, Long> {

	Pagination findListByCondition(QueryObject queryObject, SysUser sysUser);

	SysUser loadUserByUsername(String username);

	SysUser save(SysUser sysUser);

	List<SysUser> findListDuplication(String usrname, Long id);
	
}
