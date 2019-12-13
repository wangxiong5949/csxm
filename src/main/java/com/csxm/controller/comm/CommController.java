package com.csxm.controller.comm;

import com.csxm.service.comm.CommService;
import com.csxm.util.ResultUtil;
import com.csxm.util.entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/comm")
@Controller
public class CommController {

    @Resource
    private CommService commService;


    /**
     * 登入的操作
     */
    @RequestMapping("/voidLogin")
    @ResponseBody
    public Map<String, Object> voidLogin(HttpServletRequest request, String userAccount,
                                         String userPwd){
        return commService.voidLogin(request, userAccount, userPwd);
    }

    /**
     * 登入页面显示
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "page/comm/login";
    }

    /**
     * 测试接口
     */
    @RequestMapping("/toText")
    @ResponseBody
    public Result toText(String id) throws Exception{
        if("1".equals(id)){
            throw new Exception("aaaa");
        } else {
            return ResultUtil.success();
        }
    }

    /**
     * 测试页面
     */
    @RequestMapping("/toTextPage")
    public ModelAndView toTextPage(){
        Map<String, Object> mData = new HashMap<>();
        mData.put("hStr","12345600000");
        return new ModelAndView("page/comm/textPage", mData);
    }


}
