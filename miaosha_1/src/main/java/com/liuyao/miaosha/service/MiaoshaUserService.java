package com.liuyao.miaosha.service;

import com.liuyao.miaosha.dao.MiaoshaUserDao;
import com.liuyao.miaosha.domain.MiaoshaUser;
import com.liuyao.miaosha.result.CodeMsg;
import com.liuyao.miaosha.util.MD5Util;
import com.liuyao.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuyao
 * @date 2018/07/23
 */
@Service
public class MiaoshaUserService {
    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(long id) {
        return miaoshaUserDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo) {
        if (loginVo == null) {
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String fromPass = loginVo.getPassword();
//        判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            return CodeMsg.MOBILE_NO_EXIST;
        }
        //验证密码
        String dbPasss = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.fromPassToDBPass(fromPass, saltDB);
        if (!calcPass.equals(dbPasss)) {
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }
}
