package com.liuyao.miaosha.result;

import lombok.Getter;
import lombok.Setter;

/**
 * Created By liuyao on 2018/07/14 10:42.
 */
@Getter
@Setter
public class Result<T> {
    private int code;
    private String msg;
    private T data;


    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public Result(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    //    成功的时候只需要传data就行
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    //失败的时候传CodeMsg
    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<T>(cm);
    }
}
