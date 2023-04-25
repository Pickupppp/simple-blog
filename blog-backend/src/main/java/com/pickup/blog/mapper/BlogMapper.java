package com.pickup.blog.mapper;

import com.pickup.blog.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pickup
 * @since 2023-04-24
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

}
