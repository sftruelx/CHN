package com.mycompany.service;

import com.mycompany.model.common.Dispatch;
import com.mycompany.service.common.DispatchManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@Transactional(Transactional.TxType.NOT_SUPPORTED)
public class DispatchManagerTest extends BaseManagerTestCase {

	 private Log log = LogFactory.getLog(DispatchManagerTest.class);

	    @Autowired
	    private DispatchManager manager;


	    @Test
	    public void testAddDispatch() throws Exception{
			Dispatch dispatch = new Dispatch();
			dispatch.setUserId(1L);
			dispatch.setDepartment(1L);
			dispatch.setCheckItem(1);
			dispatch.setCreateDate(new Date());
			dispatch.setState(0);
			Dispatch d = manager.save(dispatch);
	    	assertNotNull(d.getId());
	    }
	    
	    @Test
	    public void testPatient() throws Exception {
	        List list = manager.getAll();
	        Dispatch rc = manager.get(1L);
	        assertNotNull(rc);
	        assertNotNull(list);
//	        assertEquals(1, list.size());
	    }
	    

}
