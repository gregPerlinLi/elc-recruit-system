package com.gdutelc.recruit.utils;

import org.apache.commons.lang3.builder.ToStringStyle;

import java.lang.reflect.Field;

/**
 * @author TUFSolareyes
 * @date 22/10/02
 */
public class CsvUtil extends ToStringStyle {

    public static String getCsvStr(Object obj) {
        if(!GenericUtils.ofNullable(obj)) {
            return null;
        }
        StringBuilder str = new StringBuilder();
        Class<?> aClass = obj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for(Field f : declaredFields) {
            f.setAccessible(true);
            try {
                Object o = f.get(obj);
                String name = f.getName();
                String simpleName = o.getClass().getSimpleName();
                if(!GenericUtils.titleEngToChinese.containsKey(name)) {
                    continue;
                }
                String value = null;
                if("firstDept".equalsIgnoreCase(name) || "secondDept".equalsIgnoreCase(name)) {
                    value = GenericUtils.deptEngToChinese.get((Integer) o);
                }else if("college".equalsIgnoreCase(name)) {
                    value = GenericUtils.collegeEngToChinese.get((Integer) o);
                }else if("gender".equalsIgnoreCase(name)) {
                    value = GenericUtils.genderEngToChinese.get((Integer) o);
                }else {
                    if("String".equalsIgnoreCase(simpleName)) {
                        value = (String) o;
                    }else if("Integer".equalsIgnoreCase(simpleName)) {
                        value = String.valueOf((Integer) o);
                    }

                }
                if(value != null) {
                    str.append(value).append(",");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }


    public static String getObjectTitleStr(Object obj) {
        if(!GenericUtils.ofNullable(obj)) {
            return null;
        }

        Class<?> aClass = obj.getClass();
        StringBuilder str = new StringBuilder();
        Field[] declaredFields = aClass.getDeclaredFields();
        for(Field f : declaredFields) {
            f.setAccessible(true);
            String name = f.getName();
            if(!GenericUtils.titleEngToChinese.containsKey(name)) {
                continue;
            }
            name = GenericUtils.titleEngToChinese.get(name);
            str.append(name).append(",");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }
}
