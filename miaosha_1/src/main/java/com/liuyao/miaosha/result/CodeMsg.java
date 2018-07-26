package com.liuyao.miaosha.result;

import lombok.Getter;
import lombok.Setter;

/**
 * Created By liuyao on 2018/07/14 11:16.
 */
@Setter
@Getter
public class CodeMsg {
    private int code;
    private String msg;
    //通用模块
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常:%s");
    //登录模块
    public static CodeMsg USER_ERROR = new CodeMsg(500200, "用户输入异常");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500201, "登录密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500202, "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500203, "手机号格式错误");
    public static CodeMsg MOBILE_NO_EXIST = new CodeMsg(500204, "用户不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500205, "密码错误");

    //秒杀模块 5005xx
    public static CodeMsg MIAOSHA_OVER = new CodeMsg(500501, "商品已经被秒杀完");
    public static CodeMsg MIAOSHA_NOREPEATE = new CodeMsg(500502, "不能重复秒杀");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 变长的错误参数
     *
     * @param args
     * @return
     */
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }
}
