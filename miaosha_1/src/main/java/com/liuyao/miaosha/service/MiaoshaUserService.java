package com.liuyao.miaosha.service;

import com.liuyao.miaosha.dao.MiaoshaUserDao;
import com.liuyao.miaosha.domain.MiaoshaUser;
import com.liuyao.miaosha.exception.GlobalException;
import com.liuyao.miaosha.redis.MiaoshaUserKey;
import com.liuyao.miaosha.redis.RedisService;
import com.liuyao.miaosha.result.CodeMsg;
import com.liuyao.miaosha.util.MD5Util;
import com.liuyao.miaosha.util.UUIDUtil;
import com.liuyao.miaosha.vo.LoginVo;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuyao
 * @date 2018/07/23
 */
@Service
public class MiaoshaUserService {
    public static final String COOKIE_NAME_TOKEN = "token";
    @Autowired
    MiaoshaUserDao miaoshaUserDao;
    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(long id) {
        return miaoshaUserDao.getById(id);
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String fromPass = loginVo.getPassword();
//        判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NO_EXIST);
        }
        //验证密码
        String dbPasss = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.fromPassToDBPass(fromPass, saltDB);
        if (!calcPass.equals(dbPasss)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        addCookie(response, user);
        return true;
    }

    private void addCookie(HttpServletResponse response, MiaoshaUser user) {
        //        生成cookie
        String token = UUIDUtil.uuid();
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser miaoshaUser = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
//        延长有效期，相当于再设置一遍
        if (miaoshaUser != null) {
            addCookie(response, miaoshaUser);
        }
        return miaoshaUser;
    }
}
