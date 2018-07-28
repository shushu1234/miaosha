package com.liuyao.miaosha.redis;

/**
 * Created By liuyao on 2018/07/16 14:17.
 */
public class OrdersKey extends BasePrefix {

    public OrdersKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static OrdersKey getMiaoshaOrderByUseridGoodsid = new OrdersKey(0, "mobug");
}
