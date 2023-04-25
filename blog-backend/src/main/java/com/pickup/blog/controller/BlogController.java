package com.pickup.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pickup.blog.common.lang.Result;
import com.pickup.blog.entity.Blog;
import com.pickup.blog.entity.User;
import com.pickup.blog.service.BlogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pickup
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    BlogService blogService;

    @GetMapping("/list")
    public Result<Page<Blog>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        Page<Blog> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Blog::getUpdateTime);
        blogService.page(pageInfo, queryWrapper);
        return Result.success(pageInfo);
    }

    @GetMapping("/{id}")
    public Result<Blog> detail(@PathVariable("id") Long id) {
        Blog blog = blogService.getById(id);
        return Result.success(blog);
    }

    @PostMapping("/edit")
    @RequiresAuthentication
    public Result<Blog> edit(@Validated @RequestBody Blog blog) {
        // 编辑博客和新增博客使用相同方法
        if (blog.getId() == null) {
            // 新增博客
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            blog.setUserId(user.getId());
            blog.setCreateTime(LocalDateTime.now());
            blog.setUpdateTime(LocalDateTime.now());
            blogService.save(blog);
        } else {
            // 编辑博客
            blogService.updateById(blog);
        }
        return Result.success(blog);
    }

}
