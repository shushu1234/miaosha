package com.liuyao.miaosha.service;

import com.liuyao.miaosha.dao.GoodsDao;
import com.liuyao.miaosha.dao.OrdersDao;
import com.liuyao.miaosha.domain.MiaoshaOrder;
import com.liuyao.miaosha.domain.MiaoshaUser;
import com.liuyao.miaosha.domain.OrderInfo;
import com.liuyao.miaosha.redis.OrdersKey;
import com.liuyao.miaosha.redis.RedisService;
import com.liuyao.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created By liuyao on 2018/07/15 9:33.
 */
@Service
public class OrdersService {

    @Autowired
    OrdersDao ordersDao;

    @Autowired
    RedisService redisService;

    public MiaoshaOrder getMiaoshaOrderByUserIdAndGoodsId(Long userid, long goodsId) {
//        return ordersDao.getMiaoshaOrderByUserIdAndGoodsId(userid, goodsId);
        return redisService.get(OrdersKey.getMiaoshaOrderByUseridGoodsid, "" + userid + "_" + goodsId, MiaoshaOrder.class);

    }

    @Transactional  // 防止一个用户只能秒杀一个商品，可以添加唯一userid和goodid索引
    public OrderInfo createOrder(MiaoshaUser miaoshaUser, GoodsVo goodsVo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(miaoshaUser.getId());
        Long orderId = ordersDao.insert(orderInfo);

        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goodsVo.getId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(miaoshaUser.getId());
        ordersDao.insertMiaoshaOrder(miaoshaOrder);

//        将订单放入缓存，下次判断是否秒杀成功可以直接从缓存中取
        redisService.get(OrdersKey.getMiaoshaOrderByUseridGoodsid, "" + miaoshaOrder.getId() + "_" + goodsVo.getId(), MiaoshaOrder.class);

        return orderInfo;
    }
}
