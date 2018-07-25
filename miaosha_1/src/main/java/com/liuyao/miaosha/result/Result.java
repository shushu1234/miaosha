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

    /**
     * 成功的时候只需要传data数据就行
     *
     * @param data 成功时的数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 失败的时候传CodeMsg
     *
     * @param cm  失败的CodeMsg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<T>(cm);
    }
}
