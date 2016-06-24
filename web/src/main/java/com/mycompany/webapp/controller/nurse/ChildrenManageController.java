package com.mycompany.webapp.controller.nurse;

import com.mycompany.model.nurse.BizPatient;
import com.mycompany.model.sys.SysUser;
import com.mycompany.service.nurse.BizPatientManager;
import com.mycompany.util.GeneratorIdUtil;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 吴愿 on 2016/6/14.
 */


@Controller
@RequestMapping("/children")
public class ChildrenManageController {

    @Autowired
    BizPatientManager bizPatientManager;


    @RequestMapping(value = "/childrenManagePage")
    public String childrenInfo() {
        return "/nurse/childrenFilesManage";
    }

    @ResponseBody
    @RequestMapping(value = "/getPatient")
    public Pagination getPatient(QueryObject query, BizPatient bizPatient) {
        Pagination page = bizPatientManager.findListByCondition(query, bizPatient);
        return page;
    }

    @RequestMapping(value = "/getOutpatientNO")
    @ResponseBody
    public String getNO() {
        try {
            return "M" + GeneratorIdUtil.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping(value = "/addPatient")
    @ResponseBody
    public Boolean addPatient(BizPatient bizPatient, HttpServletRequest request) {
        Boolean flag = false;
        SecurityContext obj = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        SysUser user = (SysUser) obj.getAuthentication().getPrincipal();
        try {
            SimpleDateFormat birthday = new SimpleDateFormat("yyyy-MM-dd");
            bizPatient.setBirthday(birthday.parse(bizPatient.getBirthdayStr()));
            if (bizPatient.getId() != null) {
                bizPatient.setUpdateDate(new Date());
                bizPatient.setUpdateUser(user.getName());
                BizPatient patient = bizPatientManager.get(bizPatient.getId());
                bizPatient.setCreateDate(patient.getCreateDate());
            } else {
                bizPatient.setCreateUser(user.getName());
                bizPatient.setCreateDate(new Date());
            }
            bizPatientManager.save(bizPatient);
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @ResponseBody
    @RequestMapping(value = "/getChildrenById/{id}")
    public BizPatient getChildrenById(@PathVariable Long id) {
        BizPatient bizPatient = bizPatientManager.get(id);
        return bizPatient;
    }
}
