package com.mycompany.dao;

import com.mycompany.dao.common.LookupDao;
import com.mycompany.dao.sys.SysRoleDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * This class tests the current LookupDao implementation class
 * @author mraible
 */
public class SysRoleDaoTest extends BaseDaoTestCase {
    @Autowired
    SysRoleDao sysRoleDao;

}
