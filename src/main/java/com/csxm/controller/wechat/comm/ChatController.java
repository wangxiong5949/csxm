package com.csxm.controller.wechat.comm;

import com.csxm.entity.UserData;
import com.csxm.entity.wechat.WechatUserData;
import com.csxm.service.wechat.comm.ChatService;
import com.csxm.util.WebUtil;
import com.csxm.util.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信端聊天类
 */
@RequestMapping("/wechatChat")
@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;


    /**
     * 聊天的操作
     */
    @RequestMapping("/voidChat")
    @ResponseBody
    public Result voidChat(HttpServletRequest request, WechatUserData wud){
        return chatService.voidChat(request, wud);
    }

    /**
     * 聊天首页显示
     */
    @RequestMapping("/toChatIndex")
    public ModelAndView toChatIndex(HttpServletRequest request){
        //登入人基本信息
        UserData ud = WebUtil.getUserData(request);
        String userId = "weiDengLuId";
        String userName = "weiDengLuUserName";
        if(ud != null){
            userId = ud.getUserId();
            userName = ud.getUserName();
        }
        WechatUserData wud = new WechatUserData();
        wud.setUserId(userId);
        wud.setUserName(userName);
        //登入人主面板列表信息
        String xxStr = "mine: {\n" +
                "  \"username\": \"" + userName + "\",\n" + //我的昵称
                "  \"id\": \"" + userId + "\",\n" + //我的ID
                "  \"avatar\": \"http://tvax1.sinaimg.cn/crop.0.0.300.300.180/006Iv8Wjly8ff7ghbigcij308c08ct8i.jpg\",\n" + //我的头像
                "  \"sign\": \"懒得签名\"\n" + //我的签名
                "},\n" +
                "friend: [\n" +
                "    {\n" +
                "      \"groupname\": \"前端码屌\",\n" +
                "      \"id\": 1,\n" +
                "      \"online\": 2,\n" +
                "      \"list\": [{\n" +
                "        \"username\": \"杨不同\",\n" +
                "        \"id\": \"YangBuTong\",\n" +
                "        \"avatar\": \"http://tp1.sinaimg.cn/1571889140/180/40030060651/1\",\n" +
                "        \"sign\": \"这些都是测试数据，实际使用请严格按照该格式返回\"\n" +
                "      },{\n" +
                "        \"username\": \"王总\",\n" +
                "        \"id\": \"13301259389\",\n" +
                "        \"avatar\": \"http://tva3.sinaimg.cn/crop.0.0.512.512.180/8693225ajw8f2rt20ptykj20e80e8weu.jpg\",\n" +
                "        \"sign\": \"微电商达人\"\n" +
                "      }]\n" +
                "    },\n" +
                "    {\n" +
                "      \"groupname\": \"网红\",\n" +
                "      \"id\": 2,\n" +
                "      \"online\": 3,\n" +
                "      \"list\": [{\n" +
                "        \"username\": \"王雄\",\n" +
                "        \"id\": \"18388103080\",\n" +
                "        \"avatar\": \"http://tp1.sinaimg.cn/1241679004/180/5743814375/0\",\n" +
                "        \"sign\": \"在自己实力不济的时候，不要去相信什么媒体和记者。他们不是善良的人，有时候候他们的采访对当事人而言就是陷阱\"\n" +
                "      },{\n" +
                "        \"username\": \"长泽梓Azusa\",\n" +
                "        \"id\": \"100001222\",\n" +
                "        \"sign\": \"我是日本女艺人长泽あずさ\",\n" +
                "        \"avatar\": \"http://tva1.sinaimg.cn/crop.0.0.180.180.180/86b15b6cjw1e8qgp5bmzyj2050050aa8.jpg\"\n" +
                "      }]\n" +
                "    },\n" +
                "    {\n" +
                "      \"groupname\": \"我心中的女神\",\n" +
                "      \"id\": 3,\n" +
                "      \"online\": 1,\n" +
                "      \"list\": [{\n" +
                "        \"username\": \"林心如\",\n" +
                "        \"id\": \"76543\",\n" +
                "        \"avatar\": \"http://tp3.sinaimg.cn/1223762662/180/5741707953/0\",\n" +
                "        \"sign\": \"我爱贤心\"\n" +
                "      },{\n" +
                "        \"username\": \"佟丽娅\",\n" +
                "        \"id\": \"4803920\",\n" +
                "        \"avatar\": \"http://tp4.sinaimg.cn/1345566427/180/5730976522/0\",\n" +
                "        \"sign\": \"我也爱贤心吖吖啊\"\n" +
                "      }]\n" +
                "    }\n" +
                "],\n" +
                "\"group\": [{\n" +
                "  \"groupname\": \"前端群\",\n" +
                "  \"id\": \"101\",\n" +
                "  \"avatar\": \"http://tp2.sinaimg.cn/2211874245/180/40050524279/0\"\n" +
                "},{\n" +
                "  \"groupname\": \"Fly社区官方群1\",\n" +
                "  \"id\": \"102\",\n" +
                "  \"avatar\": \"http://tp2.sinaimg.cn/5488749285/50/5719808192/1\"\n" +
                "}]\n";

        Map<String, Object> mData = new HashMap<>();
        mData.put("wud", wud);
        mData.put("xxStr", xxStr);
        return new ModelAndView("page/wechat/comm/chatIndex", mData);
    }

}
