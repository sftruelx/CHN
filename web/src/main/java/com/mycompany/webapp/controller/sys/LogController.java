package com.mycompany.webapp.controller.sys;

import com.mycompany.model.sys.SysLog;
import com.mycompany.service.sys.SysLogManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liaoxiang on 2016/6/7.
 */

@Controller
@RequestMapping("/sys")
public class LogController {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysLogManager sysLogManager;

    @RequestMapping(value = "/log")
    public String showLog(HttpServletRequest request) {

        return "/sys/log";
    }


    @ResponseBody
    @RequestMapping(value = "/getLogs")
    public Pagination getPatient(QueryObject query, SysLog sysLog) {
        Pagination page = sysLogManager.findListByCondition(query, sysLog);
        return page;
    }
}
