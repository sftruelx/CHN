package com.mycompany.webapp.controller.nurse;

import com.mycompany.model.common.ActionMsg;
import com.mycompany.model.common.BizItem;
import com.mycompany.model.nurse.BizHisPatient;
import com.mycompany.model.nurse.BizPatient;
import com.mycompany.model.nurse.Triage;
import com.mycompany.model.nurse.TriageDetail;
import com.mycompany.model.sys.Dictionary;
import com.mycompany.model.sys.SysUser;
import com.mycompany.service.common.IBizItemService;
import com.mycompany.service.nurse.BizPatientManager;
import com.mycompany.service.nurse.ITriageDetailService;
import com.mycompany.service.nurse.ITriageService;
import com.mycompany.service.sys.IDictionaryService;
import com.mycompany.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangsd on 2016/6/15.   分诊管理控制层
 */

@Controller
@RequestMapping("/triage")
public class TriageController {

    @Autowired
    private ITriageService triageService;

    @Autowired
    private IDictionaryService dictionaryService;

    @Autowired
    BizPatientManager bizPatientManager;

    @Autowired
    IBizItemService bizItemService;

    @Autowired
    private ITriageDetailService triageDetailService;

    // 分诊管理页面
    @RequestMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("searchDate", DateUtils.formatSimpleDate(DateUtils.now()));
        model.addAttribute("bizItem", bizItemService.getAll());
       return "/nurse/triage";
    }

    @ResponseBody
    @RequestMapping(value = "/getBizStatus")
    public List<Dictionary> getBizStatus() {
        List<Dictionary> list = dictionaryService.findDictByParent("bizStatus");
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/getDepartment")
    public List<Dictionary> getDepartment() {
        List<Dictionary> list = dictionaryService.findDictByParent("department");
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/getPatient")
    public ActionMsg getPatient(String searchNo) {
        ActionMsg msg=new ActionMsg();
        try {
            BizHisPatient bizHisPatient= bizPatientManager.findHisNo(searchNo);
            if (bizHisPatient!=null){
                Map map = new HashMap();
                map.put("patient",bizHisPatient);
                msg.setData(map);
            }else {
                msg.setSuccess(false);
                msg.setMsg("获取信息为空");
            }
        }catch (Exception e){
            msg.setSuccess(false);
            msg.setMsg("获取信息异常");
        }
        return msg;
    }

    // 根据分页条件获取列表
    @ResponseBody
    @RequestMapping(value = "/listDetail")
    public Pagination listDetail(QueryObject query,String regStartDate,String regEndDate,String status) {
        return triageService.findListDetail(query,regStartDate,regEndDate,status);
    }

    // 保存项目  wsd on 2016/6/15
    @ResponseBody
    @RequestMapping(value = "/saveTriageData")
    public ActionMsg saveTriageData(BizHisPatient bizHisPatient,HttpServletRequest request){
        ActionMsg msg= new ActionMsg();
        try {
            SecurityContext obj = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
            SysUser user = (SysUser) obj.getAuthentication().getPrincipal();
            BizPatient bizPatient = new BizPatient();
            BeanUtils.copyProperties(bizHisPatient,bizPatient);
            msg=bizPatientManager.saveBizPatientRecord(request,bizPatient);
            if (msg.isSuccess()==true){
                bizPatient = (BizPatient) msg.getData().get("bizPatient");
                Triage triage=new Triage();
                BeanUtils.copyProperties(bizHisPatient,triage);
                if(StringUtils.equals(bizPatient.getOutpatientNo(),bizHisPatient.getOutpatientNo())){
                    triage.setOutpatientNo(bizPatient.getOutpatientNo());
                }
                triage.setOperator(user.getUsername());
                triage.setOperatorName(user.getName());
                triage.setTriageNo(String.valueOf(GeneratorIdUtil.getId()));
                triage.setStatus(1);
                triageService.save(triage);
                msg.setClosed(true);
            }

        }catch (Exception ex){
            ex.printStackTrace();
            msg.setMsg("异常了");
            msg.setSuccess(false);
            return msg;
        }

        return msg;
    }

    // 保存项目  wsd on 2016/6/15
    @ResponseBody
    @RequestMapping(value = "/saveTriageItem")
    public ActionMsg saveTriageItem(String triageNo,String name,Integer gender,String outpatientNo,String[] item,String submitType,HttpServletRequest request){
        ActionMsg msg= new ActionMsg();
        try {
            SecurityContext obj = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
            SysUser user = (SysUser) obj.getAuthentication().getPrincipal();
            if(item.length>0) {
                Triage triage = triageService.findTriageByNo(triageNo);

                if(triage!=null&& StringUtils.equals(submitType,"reAdd")) {
                    triage.setTriageItem(StringUtils.join(item,","));
                    triage.setStatus(2);
                    triageService.save(triage);
                }

            }

        }catch (Exception ex){
            ex.printStackTrace();
            msg.setMsg("异常了");
            msg.setSuccess(false);
            return msg;
        }

        return msg;
    }

    // 作废  wsd
    @ResponseBody
    @RequestMapping(value = "/cancelTriage",method = RequestMethod.POST)
    public ActionMsg cancelTriage(Long id,String triageNo){
        ActionMsg msg= new ActionMsg();
        try {
            Triage triage=triageService.get(id);
            if(triage!=null&& StringUtils.equals(triage.getTriageNo(),triageNo)){
                triage.setStatus(0);
                triageService.save(triage);
                msg.setSuccess(true);
            }else {
                msg.setSuccess(false);
                msg.setMsg("作废失败");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            msg.setMsg("异常了");
            msg.setSuccess(false);
        }

        return msg;
    }

    // 作废  wsd
    @ResponseBody
    @RequestMapping(value = "/doTriage",method = RequestMethod.POST)
    public ActionMsg doTriage(Long id,String triageNo){
        ActionMsg msg= new ActionMsg();
        try {
            Triage triage=triageService.findTriageByNo(triageNo);
            if(triage!=null){
                String[] item=triage.getTriageItem().split(",");
                if(item.length>0) {
                    List<BizItem> bizItemList = bizItemService.getAll();
                    Map<String, BizItem> map = new HashMap<String, BizItem>();
                    for (BizItem bizItem : bizItemList) {
                        map.put(bizItem.getItemCode(), bizItem);
                    }

                    for (String itemCode : item) {
                        TriageDetail triageDetail = new TriageDetail();
                        triageDetail.setOutpatientNo(triage.getOutpatientNo());
                        triageDetail.setTriageNo(triageNo);
                        triageDetail.setName(triage.getName());
                        triageDetail.setGender(triage.getGender());
                        triageDetail.setStatus(0);
                        triageDetail.setItemCode(itemCode);
                        triageDetail.setItemName(map.get(itemCode).getItemName());
                        triageDetailService.save(triageDetail);
                    }
                }
                triage.setStatus(3);
                triageService.save(triage);
                msg.setSuccess(true);
            }else {
                msg.setSuccess(false);
                msg.setMsg("分诊失败");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            msg.setMsg("异常了");
            msg.setSuccess(false);
        }

        return msg;
    }

    // 作废  wsd
    @ResponseBody
    @RequestMapping(value = "/reBackTriage",method = RequestMethod.POST)
    public ActionMsg reBackTriage(Long id,String triageNo){
        ActionMsg msg= new ActionMsg();
        try {
            Triage triage=triageService.get(id);
            if(triage!=null&& StringUtils.equals(triage.getTriageNo(),triageNo)){
                triage.setStatus(1);
                triageService.save(triage);
                msg.setSuccess(true);
            }else {
                msg.setSuccess(false);
                msg.setMsg("撤销作废失败");
            }
            msg.setSuccess(true);
        }catch (Exception ex){
            ex.printStackTrace();
            msg.setMsg("异常了");
            msg.setSuccess(false);
        }

        return msg;
    }



}
