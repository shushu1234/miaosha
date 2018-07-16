package com.liuyao.miaosha.redis;

/**
 * Created By liuyao on 2018/07/16 13:47.
 */
public interface KeyPrefix {
    public int expireSeconds();

    public String getPrefix();
}
