package com.numsource.artproject.util;

import org.apache.commons.lang.RandomStringUtils;

public class MyUtil {
    public static String uploadPath = "//home//hk//图片//";//图片默认上传的文件夹
    /**
     * 生成一个随机的六位字符
     * @return
     */
    public static String getRandomStr(){
        return RandomStringUtils.random(6,"1234567890");
    }

}
