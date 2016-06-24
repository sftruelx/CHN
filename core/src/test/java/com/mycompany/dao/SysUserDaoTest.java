package com.mycompany.dao;

import static org.junit.Assert.assertNotNull;

import com.mycompany.dao.sys.RoleCopyDao;
import com.mycompany.dao.sys.SysRoleDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.mycompany.model.sys.RoleCopy;
import com.mycompany.model.sys.SysRole;

public class SysUserDaoTest  extends BaseDaoTestCase {
    @Autowired
    private RoleCopyDao dao;

    @Autowired
    private SysRoleDao roledao;
    
    @Test(expected=DataAccessException.class)
    public void testGetUserInvalid() throws Exception {
        // should throw DataAccessException
        dao.get(100L);
    }
    
    @Test
    public void testGetUser() throws Exception {
        RoleCopy user = dao.get(1L);

        assertNotNull(user);
//        assertEquals(new Long(4), user.getSysRole().getId());
//        assertTrue(user.isEnabled());
    }
    
    @Test
    public void testSysRole() throws Exception {
        SysRole user = roledao.get(1L);

        assertNotNull(user);
//        assertEquals(new Long(4), user.getSysRole().getId());
//        assertTrue(user.isEnabled());
    }
    
    
}
