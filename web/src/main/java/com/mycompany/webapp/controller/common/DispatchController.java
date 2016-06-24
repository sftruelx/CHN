package com.mycompany.webapp.controller.common;

import com.mycompany.service.common.DispatchManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liaoxiang on 2016/5/25.
 */


@Controller
@RequestMapping("/nurse")
public class DispatchController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DispatchManager dispatchManager;


    @RequestMapping(value = "/dispatch")
    public String getDispatch(String[] item) {
        logger.info("itme " + item.length);
        return "redirect:/demo/guideList/154";
    }
/*    public ModelAndView guideList(@PathVariable Long id) {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute("patient_id", id);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
            model.addAttribute("dateStr", format.format(new Date()));
            BizPatient patient = bizPatientManager.get(id);
            patient.setStatus(3);
            patient = bizPatientManager.save(patient);
            model.addAttribute(patient);
        } catch (SearchException se) {
            se.printStackTrace();
        }

        return new ModelAndView("demo/guideList", model.asMap());
    }*/

}
