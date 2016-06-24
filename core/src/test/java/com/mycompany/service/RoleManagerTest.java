package com.mycompany.service;

import com.mycompany.dao.nurse.BizPatientHistoryDao;
import com.mycompany.model.nurse.BizPatientHistory;
import com.mycompany.model.sys.SysRight;
import com.mycompany.service.sys.SysRightManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

@Transactional(Transactional.TxType.REQUIRED)
public class RoleManagerTest extends BaseManagerTestCase {

    private Log log = LogFactory.getLog(RoleManagerTest.class);
    @Autowired
    private BizPatientHistoryDao bizPatientHistoryDao;

    @Autowired
    private SysRightManager rightManager;

    @Test
    public void testSysRole() throws Exception {
        SysRight sr = new SysRight();
        sr.setParentId(1L);
        QueryObject queryObject = new QueryObject();
        queryObject.setOffset(0);
        queryObject.setLimit(10);
        Pagination list = rightManager.findListByCondition(queryObject, sr);

        assertNotNull(list);
//	        assertEquals(1, list.size());
    }


    @Test
    public void testHistory() throws Exception {
        BizPatientHistory b = new BizPatientHistory();
        b.setOutpatientNo("1");
        b.setPatientId(12L);
        b.setStatus(1);
        b = bizPatientHistoryDao.save(b);
        assertNotNull(b);
    }
}
