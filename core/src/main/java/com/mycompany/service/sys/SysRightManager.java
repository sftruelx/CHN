package com.mycompany.service.sys;

import java.util.List;

import com.mycompany.model.sys.SysRight;
import com.mycompany.model.sys.SysUser;
import com.mycompany.service.GenericManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;

/**
 *
 *
 *
 * */

public interface SysRightManager extends GenericManager<SysRight, Long> {

	 List<SysRight> loadRights(SysRight sysRightQueryDTO);

	 List<SysRight> loadRightsByRole(SysRight sysRightQueryDTO);

	Pagination findListByCondition(QueryObject queryObject, SysRight sysRight);

	List<SysRight> findListDuplication(String dupName, Long id);
	
}




