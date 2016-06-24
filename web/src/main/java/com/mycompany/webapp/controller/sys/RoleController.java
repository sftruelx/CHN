package com.mycompany.webapp.controller.sys;

import com.mycompany.model.sys.SysRight;
import com.mycompany.model.sys.SysRole;
import com.mycompany.service.sys.SysRoleManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import com.mycompany.webapp.controller.common.MessageInfo;
import com.mycompany.webapp.controller.common.ReturnMessage;
import com.mycompany.webapp.util.Chinese2PY;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by liaoxiang on 2016/5/30.
 */

@Controller
@RequestMapping("/sys")
public class RoleController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysRoleManager sysRoleManager;


    @RequestMapping(value = "/role")
    public String userList() {
        return "/sys/role";
    }

    @ResponseBody
    @RequestMapping(value = "/roleGetRoles")
    public Pagination getPatient(QueryObject query, SysRole sysRole) {
        Pagination page = sysRoleManager.findListByCondition(query, sysRole);
        return page;
    }


    @ResponseBody
    @RequestMapping(value = "/roleAddRole")
    public ReturnMessage addRole(SysRole sysRole) {
        ReturnMessage returnMessage = new ReturnMessage();
        logger.info("", sysRole);
        try {
            //判断是否重复
            List<SysRole> roles = sysRoleManager.findListDuplication(sysRole.getRoleName(), sysRole.getId());
            if (roles.size() > 0) {
                returnMessage.setFlag(MessageInfo.FAIL);
                returnMessage.setMessage("角色名称" + MessageInfo.DUPLICATION);
                return returnMessage;
            }
            //将名称转为拼音
            Chinese2PY hanyu = new Chinese2PY();
            if (sysRole.getId() == null) {
                Random r = new Random();
                String strPinyin = hanyu.getStringPinYin(sysRole.getRoleName());
                sysRole.setRoleSecurity("ROLE_" + strPinyin + r.nextInt(999));
            } else {
                SysRole old = sysRoleManager.get(sysRole.getId());
                sysRole.setRoleSecurity(old.getRoleSecurity());
                sysRole.setSysRights(old.getSysRights());
            }
            SysRole role = sysRoleManager.save(sysRole);
            returnMessage.setObj(role);
            returnMessage.setFlag(MessageInfo.OK);
            returnMessage.setMessage(MessageInfo.SAVE_OK);
        }catch (Exception ex){
            returnMessage.setFlag(MessageInfo.FAIL);
            returnMessage.setMessage(MessageInfo.ERROR);
        }
        return returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/roleGetRoleById/{id}")
    public SysRole getRole(@PathVariable Long id) {
        logger.info("", id);
        SysRole Role = sysRoleManager.get(id);
        return Role;
    }

    @RequestMapping(value = "/roleGetIsAuthority")
    @ResponseBody
    public Set<SysRight> getIsAuthority(Long roleId) {
        SysRole sysRole = sysRoleManager.get(roleId);
        return sysRole.getSysRights();
    }

}
