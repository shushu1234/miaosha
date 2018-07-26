package com.liuyao.miaosha.service;

import com.liuyao.miaosha.dao.GoodsDao;
import com.liuyao.miaosha.dao.OrdersDao;
import com.liuyao.miaosha.domain.MiaoshaOrder;
import com.liuyao.miaosha.domain.MiaoshaUser;
import com.liuyao.miaosha.domain.OrderInfo;
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

    public MiaoshaOrder getMiaoshaOrderByUserIdAndGoodsId(Long userid, long goodsId) {
        return ordersDao.getMiaoshaOrderByUserIdAndGoodsId(userid, goodsId);
    }

    @Transactional
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
        return orderInfo;
    }
}
