package com.liuyao.miaosha.result;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.apache.bcel.classfile.Code;

/**
 * Created By liuyao on 2018/07/14 11:16.
 */
@Setter
@Getter
public class CodeMsg {
    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg USER_ERROR = new CodeMsg(500111, "用户输入异常");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
