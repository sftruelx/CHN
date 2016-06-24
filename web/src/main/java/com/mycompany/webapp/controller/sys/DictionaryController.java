package com.mycompany.webapp.controller.sys;

import com.alibaba.fastjson.JSON;
import com.mycompany.model.common.Ztree;
import com.mycompany.model.common.ActionMsg;
import com.mycompany.model.sys.Dictionary;
import com.mycompany.service.sys.IDictionaryService;
import com.mycompany.util.Pagination;
import com.mycompany.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangsd on 2016/5/26.   字典管理控制层
 */

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private IDictionaryService dictionaryService;

    // 字典页面  wsd on 2016/6/1
    @RequestMapping(value = "/list")
    public String list(Model model) {

        List<Dictionary> listDictMain = dictionaryService.findDictByType(0);
        //List<Dictionary> listDictDetail = dictionaryService.findDictByParent("department");
        List<Ztree> listztree = new ArrayList<Ztree>();
        if (listDictMain.size() > 0) {
            for (Dictionary dictionary : listDictMain) {
                Ztree ztree = new Ztree();
                ztree.setId(dictionary.getId());
                ztree.setOpen(true);
                ztree.setCode(dictionary.getDict_key());
                ztree.setName(dictionary.getDict_value());
                ztree.setCodeParent(dictionary.getDict_parent());
                ztree.setDescribe(dictionary.getDict_describe());
                ztree.setRemark(dictionary.getDict_remark());
                ztree.setDelabled(dictionary.getDelabled());
                ztree.setType(dictionary.getDict_type());
                listztree.add(ztree);
            }
            model.addAttribute("defaultDictParent", listDictMain.get(0).getDict_key());
            model.addAttribute("defaultDictValue", listDictMain.get(0).getDict_value());
        }
        model.addAttribute("dictMain", JSON.toJSON(listztree));
        //model.addAttribute("dictDetail",JSON.toJSON(listDictDetail));
        return "/sys/dictionary";
    }

    // 根据字典dict_parent和分页条件获取子字典  wsd on 2016/6/3
    @ResponseBody
    @RequestMapping(value = "/listDetail")
    public Pagination listDetail(QueryObject query, String dict_parent) {
        return dictionaryService.findDictByParentAndQuery(query, dict_parent);
    }

    // 保存字典  wsd on 2016/6/3
    @ResponseBody
    @RequestMapping(value = "/saveDict")
    public ActionMsg addMainDict(Dictionary dictionary) {
        ActionMsg msg = new ActionMsg();
        try {

            if (dictionary.getId() == null && dictionary.getDict_type() == 0 && dictionaryService.findDictByKeyAndTypeAndParent(dictionary.getDict_key(), dictionary.getDict_type(), dictionary.getDict_parent()) != null) {
                msg.setSuccess(false);
                msg.setMsg("已存在代码为：" + dictionary.getDict_key() + " 的字典了！");
                msg.setClosed(false);
                return msg;
            }
            if (dictionary.getId() == null && dictionary.getDict_type() == 0 && dictionaryService.findDictByValueAndTypeAndParent(dictionary.getDict_value(), dictionary.getDict_type(), dictionary.getDict_parent()) != null) {
                msg.setSuccess(false);
                msg.setMsg("已存在名称为：" + dictionary.getDict_value() + " 的字典了！");
                msg.setClosed(false);
                return msg;
            }
            if (dictionary.getId() == null && dictionary.getDict_type() == 1 && dictionaryService.findDictByKeyAndTypeAndParent(dictionary.getDict_key(), dictionary.getDict_type(), dictionary.getDict_parent()) != null) {
                msg.setSuccess(false);
                msg.setMsg("已存在代码为：" + dictionary.getDict_key() + " 的明细字典了！");
                msg.setClosed(false);
                return msg;
            }
            if (dictionary.getId() == null && dictionary.getDict_type() == 1 && dictionaryService.findDictByValueAndTypeAndParent(dictionary.getDict_value(), dictionary.getDict_type(), dictionary.getDict_parent()) != null) {
                msg.setSuccess(false);
                msg.setMsg("已存在名称为：" + dictionary.getDict_value() + " 的明细字典了！");
                msg.setClosed(false);
                return msg;
            }
            dictionaryService.save(dictionary);
            /*List<Dictionary> listDictMain = dictionaryService.findDictByType(0);*/
            dictionary = dictionaryService.findDictByKeyAndTypeAndParent(dictionary.getDict_key(), dictionary.getDict_type(), dictionary.getDict_parent());
            Ztree ztree = new Ztree();
            ztree.setId(dictionary.getId());
            ztree.setOpen(true);
            ztree.setCode(dictionary.getDict_key());
            ztree.setName(dictionary.getDict_value());
            ztree.setCodeParent(dictionary.getDict_parent());
            ztree.setDescribe(dictionary.getDict_describe());
            ztree.setRemark(dictionary.getDict_remark());
            ztree.setDelabled(dictionary.getDelabled());
            ztree.setType(dictionary.getDict_type());

            Map map = new HashMap();
            map.put("newDict", ztree);
            msg.setData(map);
            msg.setSuccess(true);
            msg.setMsg("保存成功");
        } catch (Exception ex) {
            ex.printStackTrace();
            msg.setMsg("异常了");
            msg.setSuccess(false);
            return msg;
        }

        return msg;
    }

    // 删除主字典  wsd on 2016/6/3
    @ResponseBody
    @RequestMapping(value = "/delDict", method = RequestMethod.POST)
    public ActionMsg delDict(Long id, String dict_key, String dict_value, String dict_type) {
        ActionMsg msg = new ActionMsg();
        try {
            if ("0".equals(dict_type)) {
                List<Dictionary> listDetail = dictionaryService.findDictByParent(dict_key);
                if (listDetail.size() > 0) {
                    msg.setSuccess(false);
                    msg.setMsg(dict_value + " 有明细字典，不允许删除");
                    return msg;
                }
            }
            dictionaryService.remove(id);
            msg.setSuccess(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            msg.setMsg("异常了");
            msg.setSuccess(false);
        }

        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/checkFieldKey", method = RequestMethod.POST)
    public Object checkFieldKey(String dict_key, Integer dict_type, String dict_parent) {
        return dictionaryService.findDictByKeyAndTypeAndParent(dict_key, dict_type, dict_parent) == null ? true : false;
    }

    @ResponseBody
    @RequestMapping(value = "/checkFieldValue", method = RequestMethod.POST)
    public Object checkFieldValue(String dict_value, Integer dict_type, String dict_parent) {
        return dictionaryService.findDictByValueAndTypeAndParent(dict_value, dict_type, dict_parent) == null ? true : false;
    }

}
