package com.gdutelc.recruit.pojo;

import lombok.Data;
import lombok.Getter;

@Data
public class ResultVO {

    @Getter
    private Integer code;

    @Getter
    private String msg;

    @Getter
    private Object data;

    private ResultVO(){

    }

    private ResultVO(Integer code,String msg,Object data){

    }

    public static ResultVO success(String msg,Object data){
        return new ResultVO(200,msg,data);
    }

    public static ResultVO expection(String msg,Object data){
        return new ResultVO(204,msg,data);
    }

    public static ResultVO error(String msg,Object data){
        return new ResultVO(500,msg,data);
    }
}
