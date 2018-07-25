package com.liuyao.miaosha.controller;

import com.liuyao.miaosha.redis.RedisService;
import com.liuyao.miaosha.result.CodeMsg;
import com.liuyao.miaosha.result.Result;
import com.liuyao.miaosha.service.MiaoshaUserService;
import com.liuyao.miaosha.util.ValidatorUtil;
import com.liuyao.miaosha.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created By liuyao on 2018/07/14 10:22.
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_login")
    public String hello() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info("{}", loginVo);
        miaoshaUserService.login(response, loginVo);
        return Result.success(true);
    }


}
