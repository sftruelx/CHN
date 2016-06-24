package com.mycompany.webapp.controller;

import com.mycompany.dao.common.SearchException;
import com.mycompany.model.nurse.BizPatient;
import com.mycompany.service.nurse.BizPatientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liaoxiang on 2016/4/14.
 */


@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    BizPatientManager bizPatientManager;
    @Autowired
    private SessionRegistry sessionRegistry;

    @RequestMapping(value = "/test")
    public String test() {
        return "/demo/test";
    }

    @RequestMapping(value = "/form")
    public String form() {
        return "/demo/form";
    }

    @RequestMapping(value = "/flot")
    public String flot(HttpServletRequest request) {
        final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

        for(final Object principal : allPrincipals) {
            if(principal instanceof UserDetails) {
                final UserDetails user = (UserDetails) principal;
                request.setAttribute("MyUser",user);
                // Do something with user
                System.out.println(user);
            }
        }

        return "/demo/flot";
    }

    @RequestMapping(value = "/morris")
    public String morris() {
        return "/demo/morris";
    }

    @RequestMapping(value = "/tables")
    public String tables() {
        return "/demo/tables";
    }

    @RequestMapping(value = "/dispatch")
    public String dispatch() {
        return "/demo/dispatch";
    }

    @RequestMapping(value = "/lore")
    public String lore() {
        return "/demo/lore";
    }


    @RequestMapping(value = "/guideList/{id}")
    public ModelAndView guideList(@PathVariable Long id) {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute("patient_id", id);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
            model.addAttribute("dateStr", format.format(new Date()));
            BizPatient bizPatient = bizPatientManager.get(id);
            bizPatient.setStatus(3);
            bizPatient = bizPatientManager.save(bizPatient);
            model.addAttribute(bizPatient);
        } catch (SearchException se) {
            se.printStackTrace();
        }

        return new ModelAndView("demo/guideList", model.asMap());
    }

    @RequestMapping(value = "/getNO")
    @ResponseBody
    public String getNO() {
        Random r = new Random();
        return "NO" + (1 + r.nextInt(999999));
    }

    @RequestMapping(value = "/bind")
    public ModelAndView bind(String openId) {
        Map model = new HashMap();
        model.put("openId", openId);
        BizPatient bizPatient = bizPatientManager.findByOpenID(openId);
        if (bizPatient != null) {
            model.put("bizPatient", bizPatient);
        }
        return new ModelAndView("demo/bind", model);
    }

    @RequestMapping(value = "/trainning")
    public String trainning() {
        return "/demo/trainning";
    }

    @RequestMapping(value = "/guide")
    public ModelAndView guide(BizPatient bizPatient) {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(bizPatientManager.get(bizPatient.getId()));
            model.addAttribute("patient_id", bizPatient.getId());
        } catch (SearchException se) {

        }
        return new ModelAndView("demo/guide", model.asMap());
    }

    @RequestMapping(value = "/nutritionalDiet")
    public String nutritionalDiet() {
        return "/demo/nutritionalDiet";
    }

    @RequestMapping(value = "/noReport")
    public String noReport() {
        return "/demo/noReport";
    }

}
