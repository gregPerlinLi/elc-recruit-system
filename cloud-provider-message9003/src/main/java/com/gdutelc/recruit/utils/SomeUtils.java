package com.gdutelc.recruit.utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class SomeUtils {

    public static String getValueFromFile(String key){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("wx_urls");
        return resourceBundle.getString(key);
    }
}
