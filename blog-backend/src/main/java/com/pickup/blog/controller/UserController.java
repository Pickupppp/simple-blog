package com.pickup.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pickup.blog.common.lang.Result;
import com.pickup.blog.dto.LoginDto;
import com.pickup.blog.dto.RegisterDto;
import com.pickup.blog.entity.User;
import com.pickup.blog.service.UserService;
import com.pickup.blog.util.BcryptUtil;
import com.pickup.blog.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    JwtUtil jwtUtil;

    @PostMapping("/register")
    public Result<User> register(@Validated @RequestBody RegisterDto registerDto) {
        User user = new User();
        BeanUtils.copyProperties(registerDto, user);
        String salt = BcryptUtil.gensalt();
        user.setPassword(BcryptUtil.encode(user.getPassword(), salt));
        user.setSalt(salt);
        user.setCreateTime(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());

        userService.save(user);
        return Result.success(user);
    }

    @PostMapping("/login")
    public Result<User> login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, loginDto.getUsername());
        User one = userService.getOne(queryWrapper);
        if (one == null) return Result.error("该用户不存在");

        if (!one.getPassword().equals(BcryptUtil.encode(loginDto.getPassword(), one.getSalt()))) {
            return Result.error("用户名或密码错误");
        }

        String token = jwtUtil.generateToken(one.getId());
        response.setHeader("Authorization", token);

        return Result.success(one);
    }

    @GetMapping("/logout")
    public Result<Void> logout() {
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }

    @GetMapping
    @RequiresAuthentication
    public Result<Void> index() {
        return Result.success(null);
    }
}
