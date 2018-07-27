package com.liuyao.miaosha.controller;

import com.liuyao.miaosha.domain.MiaoshaUser;
import com.liuyao.miaosha.redis.RedisService;
import com.liuyao.miaosha.result.Result;
import com.liuyao.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuyao
 * @date 2018/07/27
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    MiaoshaUserService userService;
    @Autowired
    RedisService redisService;

    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model, MiaoshaUser user) {
        return Result.success(user);
    }
}
