package com.mycompany.webapp.controller.sys;

import com.mycompany.dao.common.SearchException;
import com.mycompany.model.sys.*;
import com.mycompany.model.sys.Dictionary;
import com.mycompany.service.sys.IDictionaryService;
import com.mycompany.service.sys.SysRightManager;
import com.mycompany.service.sys.SysRoleManager;
import com.mycompany.service.sys.SysUserManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import com.mycompany.webapp.controller.common.MessageInfo;
import com.mycompany.webapp.controller.common.ReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by liaoxiang on 2016/6/7.
 */

@RequestMapping(value = "/home")
@Controller
public class HomeController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SysUserManager sysUserManager;

    @Autowired
    SysRoleManager sysRoleManager;

    @Autowired
    IDictionaryService dictionService;

    @RequestMapping(value = "/")
    public String home(HttpServletRequest request) {
        //主页菜单排序
/*        SecurityContext obj = (SecurityContext)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        SysUser user = (SysUser) obj.getAuthentication().getPrincipal();
        List list = new ArrayList<SysRole>();
        for (SysRole role: user.getSysRoles()) {
            list.addAll(role.getSysRights());
        }
        Collections.sort(list,new SysRightComparator());
        request.getSession().setAttribute("MY_MENU",list);*/
        return "/home";
    }

    @RequestMapping(value = "/home1")
    public String home1(HttpServletRequest request) {
        System.out.println("home1................");
        return "/home";
    }

    @RequestMapping(value = "/homeUserEdit")
    public ModelAndView UserEdit(HttpServletRequest request) {
        Model model = new ExtendedModelMap();
        String message = request.getParameter("Message");
        model.addAttribute("Message", message);
        return new ModelAndView("/sys/userEditForm", model.asMap());
    }

    @RequestMapping(value = "/homeUserSubmit")
    public ModelAndView userSubmit(SysUser sysUser) {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute("Message", "OK");
            ReturnMessage returnMessage = new ReturnMessage();
            //看看有没有重复的
/*            List<SysUser> users = sysUserManager.findListDuplication(sysUser.getUsrName(),sysUser.getId());
            if(users.size()>0){
                returnMessage.setFlag(MessageInfo.FAIL);
                returnMessage.setMessage("工号"+MessageInfo.DUPLICATION);
                model.addAttribute("Message",returnMessage.getMessage());
                return new ModelAndView("/sys/userEditForm", model.asMap());
            }

            String[] roles = sysUser.getRoles();
            for(int i=0;i<roles.length;i++){
                SysRole role = sysRoleManager.get(Integer.valueOf(roles[i]).longValue());
                sysUser.getSysRoles().add(role);
            }*/
            SysUser su = sysUserManager.get(sysUser.getId());
            su.setName(sysUser.getName());
            su.setGender(sysUser.getGender());
            su.setUsrType(sysUser.getUsrType());
            su.setUsrDepartment(sysUser.getUsrDepartment());
            su.setUsrPhone(sysUser.getUsrPhone());
            su.setUsrEmail(sysUser.getUsrEmail());
            su.setUsrUrl(sysUser.getUsrUrl());
            su.setCreateTime(sysUser.getCreateTime());
            SysUser user = sysUserManager.save(su);
            returnMessage.setObj(user);
            returnMessage.setFlag(MessageInfo.OK);
            returnMessage.setMessage(MessageInfo.SAVE_OK);
            model.addAttribute("Message",returnMessage.getMessage());
        } catch (SearchException se) {
            se.printStackTrace();
        }

        return new ModelAndView("redirect:/home/homeUserEdit", model.asMap());
    }

    @ResponseBody
    @RequestMapping(value = "/roleGetAllRoles")
    public List<SysRole> getAllRoles() {
        return sysRoleManager.getAll();
    }



    @ResponseBody
    @RequestMapping(value = "/userUpdatePassword")
    public SysUser updatePassword(ResetPassword password) {
        logger.info("", password);
        SysUser sysUser = sysUserManager.get(password.getUserId());
        ShaPasswordEncoder sha = new ShaPasswordEncoder();

        String old = sha.encodePassword(password.getOldPassword(), sysUser.getUsrName());
        if(sysUser.getUsrPassword().equals(old)) {
            String p = sha.encodePassword(password.getNewPassword(), sysUser.getUsrName());
            sysUser.setPassword(p);
            SysUser user = sysUserManager.save(sysUser);
            return user;
        }else {
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/userGetUserById/{id}")
    public SysUser getUser(@PathVariable Long id) {
        logger.info("", id);
        SysUser user = sysUserManager.get(id);
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/userResetPassword")
    public SysUser resetPassword(@RequestBody ResetPassword resetPassword) {
        logger.info("", resetPassword.getUserId());
        SysUser sysUser = sysUserManager.get(resetPassword.getUserId());
        if (sysUser.getId() != null) {
            //新建时设置默认密码123456
            ShaPasswordEncoder sha = new ShaPasswordEncoder();
            String p = sha.encodePassword("123456", sysUser.getUsrName());
            sysUser.setPassword(p);
            sysUser = sysUserManager.save(sysUser);
        }
        return sysUser;
    }

    @ResponseBody
    @RequestMapping(value = "/userGetUserEmployeeType")
    public List<Dictionary> getUserEmployeeType() {
//        logger.info("", id);
        List<Dictionary> list = dictionService.findDictByParent("employee_type");
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/userGetUserGender")
    public List<Dictionary> getUserGender() {
//        logger.info("", id);
        List<Dictionary> list = dictionService.findDictByParent("gender");
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/userGetUserDepartment")
    public List<Dictionary> getUserDepartment() {
//        logger.info("", id);
        List<Dictionary> list = dictionService.findDictByParent("department");
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/userGetUserActivity")
    public List<Dictionary> getUserActivity() {
//        logger.info("", id);
        List<Dictionary> list = dictionService.findDictByParent("activty_type");
        return list;
    }

    @Autowired
    private SysRightManager sysRightManager;


    @ResponseBody
    @RequestMapping(value = "/userGetUsers")
    public Pagination getPatient(QueryObject query, SysUser sysUser) {
        Pagination page = sysUserManager.findListByCondition(query, sysUser);
        return page;
    }


    @ResponseBody
    @RequestMapping(value = "/userAdd")
    public ReturnMessage addUser(SysUser sysUser) {
        ReturnMessage returnMessage = new ReturnMessage();
        logger.info("", sysUser);
        //看看有没有重复的
        List<SysUser> users = sysUserManager.findListDuplication(sysUser.getUsrName(),sysUser.getId());
        if(users.size()>0){
            returnMessage.setFlag(MessageInfo.FAIL);
            returnMessage.setMessage("工号"+MessageInfo.DUPLICATION);
            return returnMessage;
        }
        //        for (:
        //             ) {
        //
        //        }
        //        SysRole sysRole = sysRoleManager.get(sysUser.getRoleId());
        //        sysUser.getSysRole().add(sysRole);
        String[] roles = sysUser.getRoles();
        for(int i=0;i<roles.length;i++){
            SysRole role = sysRoleManager.get(Integer.valueOf(roles[i]).longValue());
            sysUser.getSysRoles().add(role);
        }
        if (sysUser.getId() == null) {
            //新建时设置默认密码123456
            ShaPasswordEncoder sha = new ShaPasswordEncoder();
            String p = sha.encodePassword("123456", sysUser.getUsrName());
            sysUser.setPassword(p);
        } else {

            SysUser su = sysUserManager.get(sysUser.getId());
            sysUser.setPassword(su.getUsrPassword());
        }
        SysUser user = sysUserManager.save(sysUser);
        returnMessage.setObj(user);
        returnMessage.setFlag(MessageInfo.OK);
        returnMessage.setMessage(MessageInfo.MSG1);
        return returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/userGetUserRights")
    public List<SysRight> userGetUserRights(@RequestBody SysUser sysuser) {
//        logger.info("", id);
        String[] rolestr = sysuser.getRoles();
        if(rolestr.length == 0) return null;
        Set<SysRight> rightSet = new HashSet();
        for(String str : rolestr) {
            SysRole role = sysRoleManager.get(Long.valueOf(str));
            rightSet.addAll(role.getSysRights());
        }
        List<SysRight>  list = new ArrayList(rightSet);
        Collections.sort(list,new SysRightComparator());
        return list;
    }
}
