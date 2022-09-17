package com.goal.errand.mapper;

import com.goal.errand.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Goal
 * @since 2022-08-10
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}
