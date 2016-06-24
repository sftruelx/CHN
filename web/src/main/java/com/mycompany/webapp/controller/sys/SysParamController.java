package com.mycompany.webapp.controller.sys;

import com.mycompany.model.sys.SysParam;
import com.mycompany.service.sys.SysParamService;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by wy on 2016/6/17
 */

@Controller
@RequestMapping("/sysParam")
public class SysParamController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysParamService sysParamService;

    @RequestMapping(value = "/sysParam")
    public String sysParam() {
        return "/sys/sysParam";
    }

    @ResponseBody
    @RequestMapping(value = "/getSysParam")
    public Pagination getPatient(QueryObject query, SysParam sysParam) {
        Pagination page = sysParamService.findListByCondition(query, sysParam);
        return page;
    }

    @ResponseBody
    @RequestMapping(value = "/addSysParam")
    public com.mycompany.model.common.ActionMsg addSysParam(SysParam sysParam) {
        com.mycompany.model.common.ActionMsg msg = new com.mycompany.model.common.ActionMsg();
        List<SysParam> list = sysParamService.findParamByCode(sysParam.getCode());
        List<SysParam> nameList = sysParamService.findParamByName(sysParam.getName());
        msg.setSuccess(false);
        if (sysParam.getId() == null) {//有ID是修改，没有则新增
            if (list.size() > 0) {
                msg.setMsg("已存在代码为：" + list.get(0).getCode() + ",请更换代码");
                return msg;
            }
            if (nameList.size() > 0) {
                msg.setMsg(nameList.get(0).getName() + ":名称已存在，请更换名称");
                return msg;
            }
        }
        sysParamService.save(sysParam);
        msg.setSuccess(true);
        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/getSysParamById/{id}")
    public SysParam getSysParamById(@PathVariable Long id) {
        logger.info("", id);
        SysParam sysParam = sysParamService.get(id);
        return sysParam;
    }

    @ResponseBody
    @RequestMapping(value = "/getSysParamByCode/{code}")
    public SysParam getSysParamByCode(@PathVariable String code) {
        logger.info("code", code);
        SysParam sysParam = new SysParam();
        List<SysParam> list = sysParamService.findParamByCode(code);
        if (list.size() > 0) {
            sysParam = list.get(0);
            return sysParam;
        }
        return sysParam;
    }

    @ResponseBody
    @RequestMapping(value = "/delSysParam")
    public Boolean delSysParam(Long id) {
        try {
            sysParamService.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
