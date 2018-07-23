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
    public Result<Boolean> doLogin(LoginVo loginVo) {
        log.info("{}", loginVo);
        String passinput = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        if (StringUtils.isEmpty(passinput)) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (StringUtils.isEmpty(mobile)) {
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if (!ValidatorUtil.isMobile(mobile)) {
            return Result.error(CodeMsg.MOBILE_ERROR);
        }
        CodeMsg cm = miaoshaUserService.login(loginVo);
        if (cm.getCode() == 0) {
            return Result.success(true);
        } else {
            return Result.error(cm);
        }
    }


}
