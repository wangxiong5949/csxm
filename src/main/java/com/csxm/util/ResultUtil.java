package com.csxm.util;

import com.csxm.enums.ReturnCode;
import com.csxm.util.entity.Result;

/**
 * 返回结果工具类
 */
public class ResultUtil {


    /**
     * 成功时返回null时的操作
     */
    public static Result success(){
        return success(null);
    }

    /**
     * 成功时返回
     */
    public static Result success(Object object){
        Result r = new Result();
        r.setCode(ReturnCode.ILONE.getValStr());
        r.setMsg("成功");
        r.setData(object);
        return r;
    }

    /**
     * 失败时返回
     */
    public static Result error(Integer code, String msg){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }


}
