package com.goal.errand.service.impl;

import com.goal.errand.entity.Order;
import com.goal.errand.mapper.OrderMapper;
import com.goal.errand.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Goal
 * @since 2022-08-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
