package com.gdutelc.recruit.entities;

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

    /**
     * 处理成功返回
     * @param msg
     * @param data
     * @return
     */
    public static ResultVO success(String msg,Object data){
        return new ResultVO(200,msg,data);
    }


    /**
     * 处理完成，但可能出现非期望情况（比如没有结果之类的）
     * @param msg
     * @param data
     * @return
     */
    public static ResultVO expection(String msg,Object data){
        return new ResultVO(204,msg,data);
    }

    /**
     * 处理过程报错
     * @param msg
     * @param data
     * @return
     */
    public static ResultVO error(String msg,Object data){
        return new ResultVO(500,msg,data);
    }

}
