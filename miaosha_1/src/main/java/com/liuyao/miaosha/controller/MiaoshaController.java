package com.liuyao.miaosha.controller;

import com.liuyao.miaosha.domain.MiaoshaOrder;
import com.liuyao.miaosha.domain.MiaoshaUser;
import com.liuyao.miaosha.domain.OrderInfo;
import com.liuyao.miaosha.redis.RedisService;
import com.liuyao.miaosha.result.CodeMsg;
import com.liuyao.miaosha.service.GoodsService;
import com.liuyao.miaosha.service.MiaoshaService;
import com.liuyao.miaosha.service.MiaoshaUserService;
import com.liuyao.miaosha.service.OrdersService;
import com.liuyao.miaosha.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created By liuyao on 2018/07/14 10:22.
 */
@Controller
@RequestMapping("/miaosha")
@Slf4j
public class MiaoshaController {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String toDetail(Model model, MiaoshaUser miaoshaUser, @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", miaoshaUser);
        if (miaoshaUser == null) {
            return "login";
        }
        //判断库存
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goodsVo.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAOSHA_OVER.getMsg());
            return "miaosha_fail";
        }
        //判断是否已秒杀成功
        MiaoshaOrder miaoshaOrder = ordersService.getMiaoshaOrderByUserIdAndGoodsId(miaoshaUser.getId(), goodsId);
        if (miaoshaOrder != null) {
            model.addAttribute("errmsg", CodeMsg.MIAOSHA_NOREPEATE.getMsg());
            return "miaosha_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(miaoshaUser, goodsVo);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goodsVo);
        return "order_detail";
    }
}
