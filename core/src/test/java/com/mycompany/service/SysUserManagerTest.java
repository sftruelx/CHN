package com.mycompany.service;

import com.mycompany.dao.common.jdbc.JdbcTemplateImpl;
import com.mycompany.model.sys.SysLog;
import com.mycompany.model.sys.SysLogName;
import com.mycompany.model.sys.SysRole;
import com.mycompany.model.sys.SysUser;
import com.mycompany.service.sys.SysLogManager;
import com.mycompany.service.sys.SysRoleManager;
import com.mycompany.service.sys.SysUserManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

@Transactional(Transactional.TxType.REQUIRED)
public class SysUserManagerTest extends BaseManagerTestCase {

	 private Log log = LogFactory.getLog(SysUserManagerTest.class);
	@Autowired
	private SysRoleManager sysRoleManager;
	    @Autowired
	    private SysUserManager sysUserManager;
	    @Autowired
	    private SysLogManager sysLogManager;

		@Autowired
		private JdbcTemplateImpl jdbc;
	    @Test
	    public void testAddPatient() throws Exception{
			SysUser sysUser = new SysUser();
			List list = sysRoleManager.getAll();
			sysUser.setName("ssssddds");
/*			sysUser.setUsrFlag(1);
			sysUser.setUsrName("sdfsdfs");
			sysUser.setSysRoles(list);
			sysUser = sysUserManager.save(sysUser);*/
	    	assertNotNull(sysUser);
	    }

	@Test
	public void testSysLog() throws Exception{
		int list = jdbc.findAllUser();




		assertNotNull(list);
	}

}
