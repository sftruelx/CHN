package com.mycompany.webapp.controller;

import com.mycompany.model.nurse.BizPatient;
import com.mycompany.model.sys.Trainning;
import com.mycompany.service.nurse.BizPatientManager;
import com.mycompany.service.sys.TrainningManager;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import com.mycompany.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liaoxiang on 2016/4/15.
 */

@Controller
@ResponseBody
@RequestMapping("/demo")
public class DemoDataController {
    private static final String appid = "wx41c9698b8348b7d6";
    private static final String secret = "e46ce562057ad2ddf10fc243328c7c12";

    @Autowired
    BizPatientManager bizPatientManager;

    @Autowired
    TrainningManager trainningManager;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/getPatient")
    public Pagination getPatient(QueryObject query, BizPatient bizPatient) {
        Pagination page = bizPatientManager.findListByCondition(query, bizPatient);
        return page;
    }

    @RequestMapping(value = "/addPatient")
    public BizPatient addPatient(BizPatient bizPatient) {
        logger.info("xx", bizPatient.toString());
        try {
            bizPatient.setStatus(1);
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            bizPatient.setCreateDate(new Date());
            bizPatient = bizPatientManager.save(bizPatient);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bizPatient;
    }

    @RequestMapping(value = "/addTrainning")
    public Trainning addTrainning(Trainning trainning) {
        logger.info("", trainning);
        BizPatient bizPatient = bizPatientManager.get(trainning.getPatient_id());
        bizPatient.setStatus(4);
        bizPatientManager.save(bizPatient);
        return trainningManager.save(trainning);
    }

    @RequestMapping(value = "/addBind")
    @ResponseBody
    public Map bindUser(HttpServletRequest request) {
        Map map = new HashMap();

        String num = request.getParameter("num");
        String name = request.getParameter("name");
        String openId = request.getParameter("openId");
        BizPatient bizPatient = new BizPatient();
        bizPatient.setName(name);
        bizPatient.setMobile(num);
        bizPatient.setOpenid(openId);

        BizPatient p = bizPatientManager.bindOpenID(name, num, openId);
        if (p != null) {
            map.put("code", 0);
        } else {
            map.put("code", 1);
        }
        return map;
    }


    @RequestMapping(value = "/getPatientByID/{id}")
    public BizPatient getPatientByID(@PathVariable Long id) {
        logger.info(id.toString());
        return bizPatientManager.get(id);
    }

    @RequestMapping("/report")
    public ModelAndView report(HttpServletRequest request) {
        String openId = WeixinUtil.getOpenid(appid, secret, request);
        BizPatient p = bizPatientManager.findByOpenID(openId);
        ModelMap model = new ModelMap();
        model.addAttribute("openId", openId);
        if (p == null) {
            return new ModelAndView("/demo/bind", model);
        } else {
            Trainning trainning = bizPatientManager.findByPatientId(p.getId());
            if (trainning == null) {
                return new ModelAndView("/demo/noReport");
            } else {
                model.addAttribute(trainning);
                return new ModelAndView("/demo/report", model);
            }

        }
    }

}
