package com.Lyrae.facedemo.base;

import com.Lyrae.facedemo.enums.ErrorCodeEnum;

/**
 * @Author:Lyrae
 */
public class Results {

    /**
     * 创建一个成功时数据、信息和状态码结果对象。
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> newSuccessResult(T data){
        return new Result(data,"success" ,true ,0);
    }

    /**
     * 创建一个失败时数据为空、信息和状态码为-10000结果对象。
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> newFailedResult(String message){
        return new Result(null,message,false,-10000);
    }

    /**
     * 创建一个失败时数据为空、信息和状态码结果对象。
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> newFailedResult(Integer code,String message){
        return new Result(null,message,false,code);
    }

    /**
     * 创建一个失败时数据为空、信息为errCodeEum类调用getDescription方法得到的信息、以及调用getCode方法得到的状态码的结果对象。
     * @param errCodeEnum
     * @param <T>
     * @return
     */
    public static <T> Result<T> newFailedResult(ErrorCodeEnum errCodeEnum){
        return new Result(null,errCodeEnum.getDescription(),false,errCodeEnum.getCode());
    }

    public static <T> Result<T> newResult(T data, String message, boolean success,Integer code) {
        Result result = new Result();
        result.setData(data);
        result.setCode(code);
        result.setSuccess(success);
        result.setMessage(message);
        return result;
    }

}
