package com.liuyao.miaosha.controller;

import com.liuyao.miaosha.domain.User;
import com.liuyao.miaosha.rabbitmq.MQSender;
import com.liuyao.miaosha.redis.RedisService;
import com.liuyao.miaosha.redis.UserKey;
import com.liuyao.miaosha.result.CodeMsg;
import com.liuyao.miaosha.result.Result;
import com.liuyao.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created By liuyao on 2018/07/14 10:22.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    MQSender sender;

    @RequestMapping("/mq")
    @ResponseBody
    Result<String> mq() {
        sender.send("hello liuyao");
        return Result.success("hello world");
    }


    @RequestMapping("/hello")
    @ResponseBody
    Result<String> hello() {
        return Result.success("hello world");
    }

    @RequestMapping("/error")
    @ResponseBody
    Result<String> error() {
//        return Result.error(CodeMsg.SERVER_ERROR);
        return Result.error(CodeMsg.USER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "liuyao");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<Long> redisGet() {
        Long v1 = redisService.get(UserKey.getById, "key1", Long.class);
        return Result.success(v1);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<User> redisSet() {
        User user = new User(1, "1111");
        boolean res = redisService.set(UserKey.getById, "1", user);
        User user1 = redisService.get(UserKey.getById, "1", User.class);
        return Result.success(user1);
    }
}
