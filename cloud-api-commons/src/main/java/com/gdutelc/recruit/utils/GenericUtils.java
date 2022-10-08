package com.gdutelc.recruit.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 通用工具类
 * @author TufSolareyes
 * @date 2022-08-06
 */
@Slf4j
public class GenericUtils {

    public static final HashMap<Integer,String> deptEngToChinese = new HashMap<>();

    public static final HashMap<Integer,String> collegeEngToChinese = new HashMap<>();

    public static final HashMap<Integer,String> genderEngToChinese = new HashMap<>();

    public static final HashMap<String,String> titleEngToChinese = new HashMap<>();

    static {
        titleEngToChinese.put("id","编号");
        //titleEngToChinese.put("openid","编号");
        titleEngToChinese.put("stuId","学号");
        titleEngToChinese.put("name","姓名");
        titleEngToChinese.put("gender","性别");
        titleEngToChinese.put("profile","个人简介");
        titleEngToChinese.put("hasJoin","已加入的社团");
        titleEngToChinese.put("firstDept","第一志愿");
        titleEngToChinese.put("secondDept","第二志愿");
        titleEngToChinese.put("major","专业");
        titleEngToChinese.put("college","学院");
        titleEngToChinese.put("clazz","班级");
        titleEngToChinese.put("phone","手机号码");
        titleEngToChinese.put("whereFind","哪里了解的电协");
        titleEngToChinese.put("skill","个人技能");
        titleEngToChinese.put("status","当前状态");
    }

    static {
        collegeEngToChinese.put(1,"机电工程学院");
        collegeEngToChinese.put(2,"自动化学院");
        collegeEngToChinese.put(3,"轻工化工学院");
        collegeEngToChinese.put(4,"信息工程学院");
        collegeEngToChinese.put(5,"土木交通工程学院");
        collegeEngToChinese.put(6,"管理学院");
        collegeEngToChinese.put(7,"计算机学院");
        collegeEngToChinese.put(8,"材料与能源学院");
        collegeEngToChinese.put(9,"环境科学与工程学院");
        collegeEngToChinese.put(10,"外国语学院");
        collegeEngToChinese.put(11,"数学与统计学院");
        collegeEngToChinese.put(12,"物理与光电工程学院");
        collegeEngToChinese.put(13,"艺术与设计学院");
        collegeEngToChinese.put(14,"法学院");
        collegeEngToChinese.put(15,"建筑与城市规划学院");
        collegeEngToChinese.put(16,"经济与贸易学院");
        collegeEngToChinese.put(17,"生物医药学院");
        collegeEngToChinese.put(18,"先进制造学院");
        collegeEngToChinese.put(19,"生态环境与资源学院");
        collegeEngToChinese.put(20,"集成电路学院");
        collegeEngToChinese.put(21,"商学院学院");
        collegeEngToChinese.put(22,"国际教育学院");
    }

    static {
        deptEngToChinese.put(0,"总列表");
        deptEngToChinese.put(1,"维修部");
        deptEngToChinese.put(2,"秘书部");
        deptEngToChinese.put(3,"项目部");
        deptEngToChinese.put(4,"实践部");
        deptEngToChinese.put(5,"外联部");
        deptEngToChinese.put(6,"网宣部");
        deptEngToChinese.put(7,"网络组");
    }

    static {
        genderEngToChinese.put(1,"男");
        genderEngToChinese.put(2,"女");
    }

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
            url.append(key).append("=").append(params.get(key)).append("&");
        }
        url.deleteCharAt(url.length()-1);
        return url.toString();
    }

    /**
     * 将实体类转换为Map
     *
     * @param object 需要转换的对象
     * @return 对象的 Map
     * @throws IllegalAccessException 非法访问异常
     */
    public static Map<String, Object> entityToMap(Object object) throws IllegalAccessException {
        if ( object == null ) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>(10);

        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }

    /**
     *  将Map转换为实体类
     *
     * @param map 需要转换的Map
     * @param entity 转换的实体类
     * @return 转换后的实体类
     * @param <T> 泛型
     * @throws NoSuchMethodException 方法不存在异常
     * @throws IllegalAccessException 非法访问异常
     * @throws InvocationTargetException 调用目标异常
     * @throws InstantiationException 实例化异常
     */
    public static <T> T mapToEntity(Map<String, Object> map, Class<T> entity) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (null == map){
            return null;
        }
        T t = entity.getDeclaredConstructor().newInstance();
        for(Field field : entity.getDeclaredFields()) {
            if (map.containsKey(field.getName())) {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                Object object = map.get(field.getName());
                if (object!= null && field.getType().isAssignableFrom(object.getClass())) {
                    field.set(t, object);
                }
                field.setAccessible(flag);
            }
        }
        return t;
    }
}
