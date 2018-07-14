package com.liuyao.miaosha.controller;

import com.liuyao.miaosha.result.CodeMsg;
import com.liuyao.miaosha.result.Result;
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
}
