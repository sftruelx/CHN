package com.mycompany.service;

import com.mycompany.service.sys.SysRoleManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@Transactional(Transactional.TxType.NOT_SUPPORTED)
public class SysRoleRightTest extends BaseManagerTestCase {

	 private Log log = LogFactory.getLog(SysRoleRightTest.class);

	    @Autowired
	    private SysRoleManager sysRoleManager;


}
