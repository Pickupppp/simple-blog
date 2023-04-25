package com.pickup.blog.service.impl;

import com.pickup.blog.entity.Blog;
import com.pickup.blog.mapper.BlogMapper;
import com.pickup.blog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
