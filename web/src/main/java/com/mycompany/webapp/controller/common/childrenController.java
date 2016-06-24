package com.mycompany.webapp.controller.common;

import com.mycompany.service.nurse.BizPatientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liaoxiang on 2016/4/14.
 */


@Controller
@RequestMapping("/nurse")
public class childrenController {

    @Autowired
    BizPatientManager bizPatientManager;


    @RequestMapping(value = "/childrenInfo")
    public String childrenInfo() {
        return "/nurse/childrenInfo";
    }

    @RequestMapping(value = "/guideList")
    public String guideList() {
        return "/nurse/guideList";
    }

}
