package com.mycompany.service.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mock;

import com.mycompany.dao.sys.SysRoleDao;

public class SysRoleManagerImplTest  extends BaseManagerMockTestCase{
	@Mock
    private SysRoleDao dao;



    @Test
    public void testGetUser() throws Exception {
        //given
//        final User testData = new User("1");
//        testData.getRoles().add(new Role("user"));

//        given(userDao.get(1L)).willReturn(testData);

        //then
//        List user = manager.getAll();
//
//        //then
//        assertTrue(user != null);
////        assert user != null;
//        assertTrue(user.size() == 2);
    }

}
