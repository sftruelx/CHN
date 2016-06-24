package com.mycompany.service.impl;

import com.mycompany.dao.nurse.BizPatientDao;
import com.mycompany.dao.nurse.BizPatientHistoryDao;
import com.mycompany.model.common.ActionMsg;
import com.mycompany.model.nurse.BizHisPatient;
import com.mycompany.model.nurse.BizPatient;
import com.mycompany.model.nurse.BizPatientHistory;
import com.mycompany.model.sys.SysUser;
import com.mycompany.model.sys.Trainning;
import com.mycompany.service.nurse.BizPatientManager;
import com.mycompany.util.GeneratorIdUtil;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liaoxiang on 2016/4/13.
 */

@Service
public class BizPatientManagerImpl extends GenericManagerImpl<BizPatient, Long> implements BizPatientManager {

    private BizPatientDao bizPatientDao;

    @Autowired
    private BizPatientHistoryDao bizPatientHistoryDao;

    @Autowired
    public BizPatientManagerImpl(BizPatientDao bizPatientDao) {
        super(bizPatientDao);
        this.bizPatientDao = bizPatientDao;
    }


    @Override
    public Pagination findListByCondition(QueryObject queryObject, BizPatient bizPatient) {
        Pagination page = bizPatientDao.findListByCondition(queryObject, bizPatient);
        return page;
    }

    public BizPatient bindOpenID(String name, String mobile, String openID) {
        BizPatient bizPatient = bizPatientDao.findByNameAndMobile(name, mobile);
        bizPatient.setOpenid(openID);
        return bizPatientDao.save(bizPatient);
    }

    public BizPatient findByOpenID(String openID) {
        return bizPatientDao.findByOpenId(openID);
    }

    @Override
    public Trainning findByPatientId(Long patientId) {
        return bizPatientDao.findByPatientId(patientId);
    }

    public BizPatient findOutpatientNo(String outpatientNo) {
        return bizPatientDao.findOutpatientNo(outpatientNo);
    }

    public BizHisPatient findHisNo(String outpatientNo) {
        String str = outpatientNo.substring(0, 1);
        BizHisPatient bizHisPatient = new BizHisPatient();
        if (str.equals("M") || str.equals("Y")) {
            BizPatient patient = bizPatientDao.findOutpatientNo(outpatientNo);
            if (patient != null) {
                BeanUtils.copyProperties(patient, bizHisPatient);
            } else {
                return null;
            }
        } else {
            bizHisPatient = bizPatientDao.findHisNo(outpatientNo);
        }

        return bizHisPatient;
    }

    @Override
    public ActionMsg saveBizPatientRecord(HttpServletRequest request, BizPatient bizPatient) {
        ActionMsg actionMsg = new ActionMsg();
        SecurityContext obj = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        SysUser user = (SysUser) obj.getAuthentication().getPrincipal();
        if (StringUtils.isEmpty(bizPatient.getOutpatientNo())) {//如果门诊号码为空
            BizPatient b = bizPatientDao.findCardId(bizPatient.getCardId());
            if (b == null) {//如果为null，继续按照医保卡查询
                b = bizPatientDao.findMedicareCard(bizPatient.getMedicareCard());
            }

            if (b == null) {//如果身份证和医保卡都是null，生成一个门诊号码
                try {
                    String outpatientNo = "M" + GeneratorIdUtil.getId();
                    bizPatient.setOutpatientNo(outpatientNo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {//按身份证或者医保卡查出来，赋值
                bizPatient.setOutpatientNo(b.getOutpatientNo());
                bizPatient = addValue(b, bizPatient);
            }

        } else {
            BizPatient b = bizPatientDao.findOutpatientNo(bizPatient.getOutpatientNo());
            BizPatientHistory bizPatientHistory = new BizPatientHistory();
            BeanUtils.copyProperties(b, bizPatientHistory);
            bizPatientHistory.setPatientId(b.getId());
            bizPatientHistory.setUpdateUser(user.getName());
            bizPatientHistoryDao.save(bizPatientHistory);//增加历史表

            if (bizPatient.getOutpatientNo().substring(0, 1).equals("M") || bizPatient.getOutpatientNo().substring(0, 1).equals("Y")) {
                BizPatient patient = findOutpatientNo(bizPatient.getOutpatientNo());
                if (patient != null) {
                    bizPatient = addValue(patient, bizPatient);
                }
            } else {
                bizPatient.setOutpatientNo("Y" + bizPatient.getOutpatientNo());
                BizPatient patient = findOutpatientNo(bizPatient.getOutpatientNo());
                if (patient != null) {
                    bizPatient = addValue(patient, bizPatient);
                }
            }
        }
        try {
            Map map = new HashMap();
            bizPatient.setStatus(1);
            bizPatient.setCreateDate(new Date());
            bizPatient.setCreateUser(user.getName());
            bizPatient.setUpdateDate(new Date());
            bizPatient.setUpdateUser(user.getName());
            bizPatient = bizPatientDao.save(bizPatient);
            actionMsg.setSuccess(true);
            map.put("bizPatient", bizPatient);
            actionMsg.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            actionMsg.setSuccess(false);
        }
        return actionMsg;
    }

    public BizPatient addValue(BizPatient patient, BizPatient bizPatient) {
        bizPatient.setId(patient.getId());
        if (StringUtils.isEmpty(bizPatient.getCardId())) {
            bizPatient.setCardId(patient.getCardId());
        }
        if (StringUtils.isEmpty(bizPatient.getMedicareCard())) {
            bizPatient.setMedicareCard(patient.getMedicareCard());
        }
        if (StringUtils.isEmpty(bizPatient.getMobile())) {
            bizPatient.setMobile(patient.getMobile());
        }
        if (StringUtils.isEmpty(bizPatient.getAddress())) {
            bizPatient.setAddress(patient.getAddress());
        }
        if (StringUtils.isEmpty(bizPatient.getNation())) {
            bizPatient.setNation(patient.getNation());
        }
        if (StringUtils.isEmpty(bizPatient.getName())) {
            bizPatient.setName(patient.getName());
        }
        if (StringUtils.isEmpty(bizPatient.getBirthday())) {
            bizPatient.setBirthday(patient.getBirthday());
        }
        return bizPatient;
    }
}
