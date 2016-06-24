package com.mycompany.webapp.controller.nurse;

import com.mycompany.model.common.ActionMsg;
import com.mycompany.model.common.BizItem;
import com.mycompany.service.nurse.ITriageService;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by wangsd on 2016/6/15.   分诊管理明细业务表控制层
 */

@Controller
@RequestMapping("/biz")
public class TriageDetailController {

    @Autowired
    private ITriageService triageService;

    // 分诊管理页面  wsd on 2016/6/15
    @RequestMapping(value = "/list")
    public String list(Model model) {

       return "/nurse/triageDetail";
    }

    // 根据分页条件获取列表  wsd on 2016/6/15
    @ResponseBody
    @RequestMapping(value = "/listDetail")
    public Pagination listDetail(QueryObject query) {
        return null;
    }




}
