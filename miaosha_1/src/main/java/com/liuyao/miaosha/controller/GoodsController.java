package com.liuyao.miaosha.controller;

import com.liuyao.miaosha.domain.Goods;
import com.liuyao.miaosha.domain.MiaoshaUser;
import com.liuyao.miaosha.domain.User;
import com.liuyao.miaosha.redis.RedisService;
import com.liuyao.miaosha.result.Result;
import com.liuyao.miaosha.service.GoodsService;
import com.liuyao.miaosha.service.MiaoshaUserService;
import com.liuyao.miaosha.vo.GoodsVo;
import com.liuyao.miaosha.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created By liuyao on 2018/07/14 10:22.
 */
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

//    @RequestMapping("/to_list")
//    public String toList(HttpServletResponse response, Model model,
//                         @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
//                         @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String paramToken) {
//        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
//            return "login";
//        }
//        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;  //有些手机端是把cookie当做参数传过来的
//        MiaoshaUser user = miaoshaUserService.getByToken(response, token);
//        model.addAttribute("user", user);
//        return "goods_list";
//    }


    @RequestMapping("/to_list")
    public String toList(Model model, MiaoshaUser miaoshaUser) {
        model.addAttribute("user", miaoshaUser);
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String toDetail(Model model, MiaoshaUser miaoshaUser, @PathVariable("goodsId") long goodsId) {
        model.addAttribute("user", miaoshaUser);
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if (now < startAt) {
            miaoshaStatus = 0;
            remainSeconds = (int) ((endAt - now) / 1000);
        } else if (now > endAt) {
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }
}
