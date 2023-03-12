package com.Lyrae.facedemo.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author:Lyrae
 * @param <T>
 */
@Getter
@Setter
public class Result<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 结果成功
     */
    private boolean success;

    /**
     * 状态信息,错误描述
     */
    private String message;

    /**
     *    泛型T 表示的是一个没有具体类型的对象，当我们没有具体指明T的类型时，它将是一个Object类型，
     *     也就是任何对象的父类，正因为如此，所以你可以在设置它时轻松的转换具体类型，这样就简化了代码，提高我们的效率。
     */
    private T data;

    /**
     * 返回集的方法重载
     */

    public Result(){
        this(true);
    }

    public Result(boolean success){
        this.success = true;
        this.success = success;
    }

    public Result(T data , String message , boolean success , Integer code){
        this.data = data;
        this.code = code;
        this.success = success;
        this.message = message;
    }
}
