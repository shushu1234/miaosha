package com.liuyao.miaosha.service;

import com.liuyao.miaosha.dao.GoodsDao;
import com.liuyao.miaosha.dao.UserDao;
import com.liuyao.miaosha.domain.Goods;
import com.liuyao.miaosha.domain.MiaoshaGoods;
import com.liuyao.miaosha.domain.User;
import com.liuyao.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created By liuyao on 2018/07/15 9:33.
 */
@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public int reduceStock(GoodsVo goodsVo) {
        MiaoshaGoods miaoshaGoods = new MiaoshaGoods();
        miaoshaGoods.setGoodsId(goodsVo.getId());
        return goodsDao.reduceStock(miaoshaGoods);
    }
}
