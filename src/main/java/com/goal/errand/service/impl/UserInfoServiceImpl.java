package com.goal.errand.service.impl;

import com.goal.errand.entity.UserInfo;
import com.goal.errand.mapper.UserInfoMapper;
import com.goal.errand.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Goal
 * @since 2022-08-10
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
