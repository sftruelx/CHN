package com.mycompany.service;

import static org.junit.Assert.assertNotNull;

import java.util.*;

import javax.transaction.Transactional;

import com.mycompany.model.nurse.BizPatient;
import com.mycompany.model.sys.*;
import com.mycompany.service.nurse.BizPatientManager;
import com.mycompany.service.sys.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional(Transactional.TxType.REQUIRED)
public class SysRoleManagerTest  extends BaseManagerTestCase {

	 private Log log = LogFactory.getLog(SysRoleManagerTest.class);

	    @Autowired
	    private RoleCopyManager roleManager;
	    @Autowired
	    private SysRoleManager sysManager;
	    @Autowired
	    private SysUserManager userManager;
	    @Autowired
	    private SysRightManager rightManager;
	    @Autowired
	    private BizPatientManager bizPatientManager;


		@Test
		public void testAddSysRole(){
			SysRole role = new SysRole();
			role.setRoleName("aaa");
			role.setRoleDesc("sdfsdf");
			role.setRoleFlag(new Integer("1"));
			role.setRoleSecurity("ROLE_TEST");
			Set<SysRight> set = new HashSet<>();
			List<SysRight> list = rightManager.getAll();
			Collections.sort(list,new SysRightComparator());
			for(SysRight r : list){
				set.add(r);
			}
			role.setSysRights(set);

			role = sysManager.save(role);
			assertNotNull(role.getId());
		}


	    @Test
	    public void testSysUser() throws Exception {
	        List list = userManager.getAll();
	        SysUser rc = userManager.loadUserByUsername("manager");
	        assertNotNull(rc);
	        assertNotNull(list);
//	        assertEquals(1, list.size());
	    }
	    
	    @Test
	    public void testSysRight() throws Exception {
	        List list = rightManager.getAll();
	        SysRight rc = rightManager.get(1L);
	        assertNotNull(rc);
	        assertNotNull(list);
//	        assertEquals(1, list.size());
	    }
	    @Test
	    public void testAddPatient() throws Exception{
	    	BizPatient p = new BizPatient();
	    	p.setName("ssss");
	    	p.setGender(1);
	    	Calendar cal = Calendar.getInstance();
	    	cal.set(2000, 1, 1);
	    	p.setBirthday(cal.getTime());
	    	p.setStatus(1);
	    	p.setCreateDate(new Date());
	    	p = bizPatientManager.save(p);
	    	assertNotNull(p);
	    }
	    
	    @Test
	    public void testPatient() throws Exception {
	        List list = bizPatientManager.getAll();
	        BizPatient rc = bizPatientManager.get(1L);
	        assertNotNull(rc);
	        assertNotNull(list);
//	        assertEquals(1, list.size());
	    }
	    
	    @Autowired
		TrainningManager trainningManager;
	    @Test
	    public void testTrainning() throws Exception {
	    	Trainning t = new Trainning();
	    	t.setContent("ssss");
	    	
	    	t = trainningManager.save(t);
	        assertNotNull(t);

//	        assertEquals(1, list.size());
	    }
}
