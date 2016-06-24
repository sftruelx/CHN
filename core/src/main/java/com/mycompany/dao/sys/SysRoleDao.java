package com.mycompany.dao.sys;


import com.mycompany.dao.common.GenericDao;
import com.mycompany.model.sys.SysRole;
import com.mycompany.model.sys.SysUser;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SysRoleDao extends GenericDao<SysRole, Long> {


	Pagination findListByCondition(QueryObject queryObject, SysRole sysRole);

	List<SysRole> findListDuplication(String dupName, Long id);
}
