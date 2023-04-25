package com.pickup.blog.util;

import cn.hutool.crypto.digest.BCrypt;
import lombok.extern.slf4j.Slf4j;

/**
 * 加密工具
 */
@Slf4j
public class BcryptUtil {

    /**
     * 生成加密盐
     * @return 加密盐
     */
    public static String gensalt(){
        return BCrypt.gensalt();
    }
    /**
     * 加密
     *
     * @param password 密码
     * @return 加密结果
     */
    public static String encode(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    /**
     * 匹配密码
     *
     * @param password       明文密码
     * @param encodePassword 加密密码
     * @return 是否匹配
     */
    public static boolean match(String password, String encodePassword) {
        return BCrypt.checkpw(password, encodePassword);
    }
}
