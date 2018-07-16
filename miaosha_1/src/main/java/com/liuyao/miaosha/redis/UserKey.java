package com.liuyao.miaosha.redis;

/**
 * Created By liuyao on 2018/07/16 14:17.
 */
public class UserKey extends BasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
