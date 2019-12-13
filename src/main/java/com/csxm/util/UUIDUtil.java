package com.csxm.util;

import java.util.UUID;

/**
 * @Description
 * @param
 * @return
 * @date 2015年11月4日上午11:38:27
 * @author 郑爽
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
    	for(int i=0;i<12360;i++){
    		System.out.println(UUIDUtil.getUUID());
    	}
        
    }
}
