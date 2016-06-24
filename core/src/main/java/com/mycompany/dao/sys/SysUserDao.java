package com.mycompany.dao.sys;

import java.util.List;

import com.mycompany.dao.common.GenericDao;
import com.mycompany.model.sys.SysUser;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.data.domain.Page;


public interface SysUserDao extends GenericDao<SysUser, Long> {

	SysUser loadUserByUsername(String username);

	Pagination findListByCondition(QueryObject queryObject, SysUser sysUser);

	List<SysUser> findListDuplication(String usrname, Long id);
}
