package com.csxm.wxutil;

import com.csxm.entity.wechat.WechatUserData;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 微信工具类
 */
public class WechatUtil {

    //appId（华远服务号）
    public static final String APPID = "wxc392f88b7a546582";
    //secret(秘钥)（华远服务号）
    public static final String SECRET = "303694a31f89f4162d07c600060d11fc";
    //原始ID（华远服务号）
    public static final String GH_ID = "gh_f2bc3085e6aa";
    //AppID(小程序ID)（华远服务号）
    public static final String SP_APPID = "";
    //AppSecret(小程序密钥)（华远服务号）
    public static final String SP_SECRET = "";
    //appId（华远服务号开放平台应用appId）
    public static final String APPID_WXHTGL = "";
    //secret(秘钥)（华远服务号开放平台应用秘钥）
    public static final String SECRET_WXHTGL = "";


    /**
     * https后面的s
     */
    public static final String HTTPS = "s";

    /**
     * 获取code
     */
    public static final String GET_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=#wechat_redirect";

    /**
     * 通过code生成服务号OpenId
     */
    public static final String GET_OPENID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";



    /**
     * 通过code生成服务号OpenId
     * @return
     */
    public static JSONObject getOpenId(String code) {
        String URL = GET_OPENID.replace("APPID", APPID).replace("SECRET", SECRET).replace("CODE", code);
        JSONObject jsonObject = voidJSONObject(URL, null);
        return jsonObject;
    }

    /**
     * 获取code
     */
    public static void getCode(HttpServletResponse response, HttpServletRequest request){
        //获取微信获取openId的code
        String param = request.getQueryString();//判断链接是否带参数
        String redirectUrl = "";
        String redirectUri = request.getScheme() + "" + HTTPS + "://" + request.getServerName() + request.getContextPath() + request.getServletPath();
        if(param != null && !"".equals(param)) {
            redirectUri += redirectUri  + "?" + param;
            redirectUrl = GET_CODE.replace("APPID", APPID).replace("REDIRECT_URI",redirectUri);
        } else {
            redirectUrl = GET_CODE.replace("APPID", APPID).replace("REDIRECT_URI",redirectUri);
        }
        //重定向获取微信code
        try {
            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法1
     * 有返回值方法
     * @param obj 参数
     * @param ADD_URL 发送地址
     */
    public static String getPostJsons(String ADD_URL,Object obj) {
        StringBuffer sb = new StringBuffer();
        try {
            //创建连接
            URL url = new URL(ADD_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Charset", "UTF-8");
            connection.connect();
            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            //发送请求
            //out.writeBytes(content);因为java里的char类型是16位的，一个char可以存储一个中文字符，在将其转换为 byte后高8位会丢失
            out.write(obj.toString().getBytes("UTF-8"));
            out.flush();
            out.close();
            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "UTF-8");
                sb.append(lines);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 获取地址里面的内容
     */
    public static String getAddressContent(String urls){
        try {
            //建立连接
            URL url = new URL(urls);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //获取输入流
            InputStream input = httpUrlConn.getInputStream();
            //将字节输入流转换为字符输入流
            InputStreamReader read = new InputStreamReader(input, "utf-8");
            //为字符输入流添加缓冲
            BufferedReader br = new BufferedReader(read);
            // 读取返回结果
            String data = br.readLine();
            String dataJson = "";
            while(data!=null)  {
                dataJson = data;
                data=br.readLine();
            }
            // 释放资源
            br.close();
            read.close();
            input.close();
            httpUrlConn.disconnect();
            return dataJson;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 公用方法，给接口发送请求
     */
    public static JSONObject voidJSONObject(String url, JSONObject json){
        if(json != null && !"".equals(json)){
            String response = getPostJsons(url, json);
            JSONObject jsonObject = JSONObject.fromObject(response);
            return jsonObject;
        } else {
            String response = getAddressContent(url);//JSON字符串
            JSONObject jsonObject = JSONObject.fromObject(response);
            return jsonObject;
        }
    }

    /**
     * 从session中获取微信端登入用户信息
     */
    public static WechatUserData getWechatUserData(HttpServletRequest request) {
        return (WechatUserData) request.getSession().getAttribute("wechatUserData");
    }
}
