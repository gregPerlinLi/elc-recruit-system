package com.gdutelc.recruit.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 通用工具类
 * @author TufSolareyes
 * @date 2022-08-06
 */
public class GenericUtils {

    /**
     * 标准时间模式串
     */
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

    /**
     * 生成时间字符串，精度到秒
     * @author TufSolareyes
     * @date 2022-08-06
     * @return String
     */
    public static String getFullTimeStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String s = sdf.format(date);
        return s;
    }


    /**
     * 按照模式串生成时间字符串，模式自定义
     * @author TufSolareyes
     * @date 2022-08-06
     * @param pattern 时间模式串，标准格式：yyyy-MM-dd HH:mm:ss SSS，可以自定义生成精度，比如yyyy-MM-dd就是年月日
     * @return String
     */
    public static String getFullTimeStr(String pattern){
        SimpleDateFormat sdf = null;
        Date date = new Date();
        if(!ofNullable(pattern)||!TIME_PATTERN.contains(pattern)){
            sdf = new SimpleDateFormat(TIME_PATTERN);
            return sdf.format(date);
        }
        sdf = new SimpleDateFormat(pattern);
        String s = sdf.format(date);
        return s;
    }

    /**
     * 判断多个参数是否为null，如果是字符串，则长度为0也算空
     * @author TufSolareyes
     * @date 2022-08-06
     * @param args ...
     * @return boolean
     */
    public static boolean allOfNullable(Object... args){
        for (Object a:args){
            if(a instanceof String){
                if(a==null||((String) a).length() == 0){
                    return false;
                }
            }else{
                if(a==null){
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean allOfNullable(Object obj) throws IllegalAccessException {
        if(obj == null){
            return false;
        }
        Class c = obj.getClass();
        Field[] fields = c.getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            if(field.get(obj) == null){
                return false;
            }
        }
        return true;
    }


    /**
     * 判断一个参数是否是null
     * @author TufSolareyes
     * @date 2022-08-06
     * @param o
     * @return
     */
    public static boolean ofNullable(Object o){
        if(o == null){
            return false;
        }else{
            return true;
        }
    }


    /**
     * 简单拼接url，一般用于get请求的url拼接参数
     * @author TufSolareyes
     * @date 2022-08-06
     * @param sourceUrl 不带参数的url
     * @param params 键值对形式的参数，格式为 参数名,值
     * @return String
     */
    public static String splicingUrlStr(String sourceUrl, Map<String,String> params){
        if(!allOfNullable(sourceUrl,params)){
            return sourceUrl;
        }
        StringBuilder url = new StringBuilder(sourceUrl);
        url.append("?");
        Set<String> keys = params.keySet();
        for (String key : keys){
            if(!ofNullable(key)&&ofNullable(params.get(key))){
                return sourceUrl;
            }
            key = key + "=";
            url.append(key).append(params.get(key)).append("&");
        }
        url.deleteCharAt(url.length()-1);
        return url.toString();
    }
}
