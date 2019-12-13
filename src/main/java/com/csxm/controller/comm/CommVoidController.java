package com.csxm.controller.comm;

import com.csxm.service.comm.CommService;
import com.csxm.util.entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/toWeb/comm")
@Controller
public class CommVoidController {

    @Resource
    private CommService commService;




    /**
     * 首页菜单显示
     */
    @RequestMapping("/showIndexMenu")
    @ResponseBody
    public Result showIndexMenu(HttpServletRequest request) throws Exception{
        System.out.println("1");
        return commService.getEamMenuRoleList(request);
    }

    /**
     * 首页显示
     */
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index.ftl";
    }
}
