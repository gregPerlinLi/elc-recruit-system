package com.gdutelc.recruit.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author TUFSolareyes
 * @date 22/08/13
 */
public class Test {

    /**
     * 维修部
     */
    int MAINTENANCE_DEPT = 1;

    /**
     * 秘书部
     */
    int SECRETARIAT_DEPT = 2;

    /**
     * 项目部
     */
    int PROJECT_DEPT = 3;

    /**
     * 实践部
     */
    int PRACTICE_DEPT = 4;

    /**
     * 外联部
     */
    int EX_RELATIONS_DEPT = 5;

    /**
     * 网宣部
     */
    int ONLINE_PUBLICITY_DEPT = 6;

    public int getMAINTENANCE_DEPT() {
        return MAINTENANCE_DEPT;
    }

    public int getSECRETARIAT_DEPT() {
        return SECRETARIAT_DEPT;
    }

    public int getPROJECT_DEPT() {
        return PROJECT_DEPT;
    }

    public int getPRACTICE_DEPT() {
        return PRACTICE_DEPT;
    }

    public int getEX_RELATIONS_DEPT() {
        return EX_RELATIONS_DEPT;
    }

    public int getONLINE_PUBLICITY_DEPT() {
        return ONLINE_PUBLICITY_DEPT;
    }

    @org.junit.jupiter.api.Test
    public void test(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(new Test());
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
