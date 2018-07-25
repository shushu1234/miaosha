package com.liuyao.miaosha.util;

import java.util.UUID;

/**
 * @author liuyao
 * @date 2018/07/24
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
