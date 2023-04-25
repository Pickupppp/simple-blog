package com.pickup.blog.service.impl;

import com.pickup.blog.entity.User;
import com.pickup.blog.mapper.UserMapper;
import com.pickup.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pickup
 * @since 2023-04-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
