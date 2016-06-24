package com.mycompany.webapp.controller.nurse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ${吴愿} on 2016/5/25.
 */
@Controller
@RequestMapping("/parentChildGame")
public class ParentChildGameController {

    @RequestMapping(value = "/page")
    public ModelAndView parentChildGamePage() {
        return new ModelAndView("nurse/parentChildGame");
    }


    @RequestMapping(value = "/ASQActionTest")
    public ModelAndView ASQActionTest() {
        return new ModelAndView("nurse/ASQActionTest");
    }

    @RequestMapping(value = "/ASQChildGrowth")
    public ModelAndView ASQChildGrowth() {
        return new ModelAndView("nurse/ASQChildGrowth");
    }

    @RequestMapping(value = "/baillyTest")
    public ModelAndView baillyTest() {
        return new ModelAndView("nurse/baillyTest");
    }


}
