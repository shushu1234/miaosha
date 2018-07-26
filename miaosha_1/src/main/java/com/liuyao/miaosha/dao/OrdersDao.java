package com.liuyao.miaosha.dao;

import com.liuyao.miaosha.domain.MiaoshaOrder;
import com.liuyao.miaosha.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

/**
 * @author liuyao
 * @date 2018/07/26
 */
@Mapper
public interface OrdersDao {
    @Select("select * from miaosha_order where user_id=#{userid} and goods_id=#{goodsid}")
    public MiaoshaOrder getMiaoshaOrderByUserIdAndGoodsId(@Param("userid") Long userid, @Param("goodsid") long goodsId);

    @Insert("insert into order_info (user_id,goods_id,delivery_addr_id,goods_name,goods_count," +
            "goods_price,order_channel,status,create_date) values(#{userId},#{goodsId},#{deliveryAddrId},#{goodsName}," +
            "#{goodsCount},#{goodsPrice},#{orderChannel},#{status},#{createDate})")
    @SelectKey(keyColumn = "id", keyProperty = "id", before = false, resultType = Long.class, statement = "select last_insert_id()")
    Long insert(OrderInfo orderInfo);

    @Insert("insert into miaosha_order (user_id,order_id,goods_id) values(#{userId},#{orderId},#{goodsId})")
    void insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
