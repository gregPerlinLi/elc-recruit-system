package com.gdutelc.recruit.utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * 通用的一些工具累，比如获取文件内容等
 * @author TufSolareyes
 * @date 2022.8.5
 */
public class SomeUtils {



    public static String getValueFromFile(String key){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("wx_urls");
        return resourceBundle.getString(key);
    }


}
