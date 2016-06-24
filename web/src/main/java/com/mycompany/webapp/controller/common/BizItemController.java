package com.mycompany.webapp.controller.common;

import com.mycompany.model.common.ActionMsg;
import com.mycompany.model.common.BizItem;
import com.mycompany.service.common.IBizItemService;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by wangsd on 2016/6/15.   检查项目控制层
 */

@Controller
@RequestMapping("/bizItem")
public class BizItemController {

    @Autowired
    private IBizItemService bizItemService;

    // 字典页面  wsd on 2016/6/1
    @RequestMapping(value = "/list")
    public String list(Model model) {

       return "/common/bizItem";
    }

    // 根据分页条件获取项目列表  wsd on 2016/6/17
    @ResponseBody
    @RequestMapping(value = "/listDetail")
    public Pagination listDetail(QueryObject query,String itemValue) {
        return bizItemService.findBizItem(query,itemValue);
    }

    // 保存项目  wsd on 2016/6/15
    @ResponseBody
    @RequestMapping(value = "/saveBizItem")
    public ActionMsg saveBizItem(BizItem bizItem,String submitType){
        ActionMsg msg= new ActionMsg();
        try {
            if (bizItem.getId()==null&&submitType.equals("add")&&bizItemService.findBizItemByCode(bizItem.getItemCode())!=null){
                msg.setSuccess(false);
                msg.setMsg("已存在代码为："+bizItem.getItemCode()+" 的项目了！");
                msg.setClosed(false);
                return msg;
            }
            if (bizItem.getId()==null&&submitType.equals("add")&&bizItemService.findBizItemByName(bizItem.getItemName())!=null){
                msg.setSuccess(false);
                msg.setMsg("已存在名称为："+bizItem.getItemName()+" 的项目了！");
                msg.setClosed(false);
                return msg;
            }
            msg.setSuccess(true);
            bizItemService.save(bizItem);
        }catch (Exception ex){
            ex.printStackTrace();
            msg.setMsg("异常了");
            msg.setSuccess(false);
            return msg;
        }

        return msg;
    }

    // 删除项目  wsd on 2016/6/15
    @ResponseBody
    @RequestMapping(value = "/delBizItem",method = RequestMethod.POST)
    public ActionMsg delBizItem(Long id, String itemCode){
        ActionMsg msg= new ActionMsg();
        try {

            msg.setSuccess(true);
        }catch (Exception ex){
            ex.printStackTrace();
            msg.setMsg("异常了");
            msg.setSuccess(false);
        }

        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/getBizItem",method = RequestMethod.POST)
    public ActionMsg getBizItem(String itemCode){
        ActionMsg msg= new ActionMsg();
        try {
            BizItem bizItem = bizItemService.findBizItemByCode(itemCode);
            if (bizItem==null){
                msg.setSuccess(false);
                msg.setMsg("获取数据异常了");
            }else {
                msg.setSuccess(true);
                Map map = new HashMap();
                map.put("bizItem",bizItem);
                msg.setData(map);
                return msg;
            }

        }catch (Exception ex){
            ex.printStackTrace();
            msg.setMsg("异常了");
            msg.setSuccess(false);
        }

        return msg;
    }


}
