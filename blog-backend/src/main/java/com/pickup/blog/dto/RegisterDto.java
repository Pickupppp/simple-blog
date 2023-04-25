package com.pickup.blog.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class RegisterDto implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String username;
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "email不能为空")
    private String email;
    @NotBlank(message = "密码不能为空")
    private String password;
}
