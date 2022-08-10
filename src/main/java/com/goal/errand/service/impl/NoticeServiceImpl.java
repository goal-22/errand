package com.goal.errand.service.impl;

import com.goal.errand.entity.Notice;
import com.goal.errand.mapper.NoticeMapper;
import com.goal.errand.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公告表 服务实现类
 * </p>
 *
 * @author Goal
 * @since 2022-08-10
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
