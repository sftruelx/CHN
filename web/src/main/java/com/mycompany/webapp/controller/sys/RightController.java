package com.mycompany.webapp.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.mycompany.model.sys.SysRight;
import com.mycompany.model.sys.SysRole;
import com.mycompany.service.sys.SysRightManager;
import com.mycompany.service.sys.SysRoleManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import com.mycompany.webapp.controller.common.MessageInfo;
import com.mycompany.webapp.controller.common.ReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liaoxiang on 2016/5/30.
 */

@Controller
@RequestMapping("/sys")
public class RightController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysRightManager sysRightManager;
    @Autowired
    private SysRoleManager sysRoleManager;


    @RequestMapping(value = "/right")
    public String userList() {
        return "/sys/right";
    }

    @ResponseBody
    @RequestMapping(value = "/rightGetRights")
    public Pagination getPatient(QueryObject query, SysRight sysRight) {
        Pagination page = sysRightManager.findListByCondition(query, sysRight);
        return page;
    }


    @ResponseBody
    @RequestMapping(value = "/rightAddRight")
    public ReturnMessage addRight(SysRight sysRight) {
        ReturnMessage returnMessage = new ReturnMessage();
        try {
            //判断是否重复
            List<SysRight> roles = sysRightManager.findListDuplication(sysRight.getRightText(), sysRight.getId());
            if (roles.size() > 0) {
                returnMessage.setFlag(MessageInfo.FAIL);
                returnMessage.setMessage("权限名称" + MessageInfo.DUPLICATION);
                return returnMessage;
            }
            if (sysRight.getId() != null) {
                SysRight right = sysRightManager.get(sysRight.getId());
                right.setRightText(sysRight.getRightText());
                right = sysRightManager.save(right);
                returnMessage.setObj(right);
                returnMessage.setFlag(MessageInfo.OK);
                returnMessage.setMessage(MessageInfo.SAVE_OK);
            }
        } catch (Exception ex) {
            returnMessage.setFlag(MessageInfo.FAIL);
            returnMessage.setMessage(MessageInfo.ERROR);
        }
        return returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/rightGetRightById/{id}")
    public SysRight getRight(@PathVariable Long id) {
        logger.info("", id);
        SysRight right = sysRightManager.get(id);
        return right;
    }

    @ResponseBody
    @RequestMapping(value = "/rightAddRoleRight")
    public SysRole addRoleRight(Long roleId, String nodes) {
        SysRole sysRole = sysRoleManager.get(roleId);
        Set<SysRight> list = new HashSet<>();
        if (nodes.isEmpty()) {
            sysRole.setSysRights(list);
        } else {
            String[] sourceStrArray = nodes.split(",");
            for (int i = 0; i < sourceStrArray.length; i++) {
                SysRight sysRight = sysRightManager.get(Long.valueOf(sourceStrArray[i]));
                list.add(sysRight);
                sysRole.setSysRights(list);
            }
        }
        SysRole role = sysRoleManager.save(sysRole);
        return role;
    }
}
