package com.liuyao.miaosha.exception;

import com.liuyao.miaosha.result.CodeMsg;
import lombok.Getter;

/**
 * @author liuyao
 * @date 2018/07/24
 */
@Getter
public class GlobalException extends RuntimeException {

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }
}
