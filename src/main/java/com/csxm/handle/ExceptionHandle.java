package com.csxm.handle;

import com.csxm.enums.ReturnCode;
import com.csxm.util.ResultUtil;
import com.csxm.util.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕获类
 */
@ControllerAdvice
public class ExceptionHandle {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        return ResultUtil.error(ReturnCode.ILTHREE.getValStr(), e.getMessage());
    }
}
