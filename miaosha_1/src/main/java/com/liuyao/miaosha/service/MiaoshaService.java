package com.liuyao.miaosha.service;

import com.liuyao.miaosha.dao.GoodsDao;
import com.liuyao.miaosha.domain.Goods;
import com.liuyao.miaosha.domain.MiaoshaUser;
import com.liuyao.miaosha.domain.OrderInfo;
import com.liuyao.miaosha.exception.GlobalException;
import com.liuyao.miaosha.result.CodeMsg;
import com.liuyao.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created By liuyao on 2018/07/15 9:33.
 */
@Service
public class MiaoshaService {
    @Autowired
    GoodsService goodsService;

    @Autowired
    OrdersService ordersService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser miaoshaUser, GoodsVo goodsVo) {
        // 减库存，下订单，写入秒杀订单
        if (goodsService.reduceStock(goodsVo) == 0) {
            throw new GlobalException(CodeMsg.MIAOSHA_OVER);
        } else {
            return ordersService.createOrder(miaoshaUser, goodsVo);
        }
    }
}
