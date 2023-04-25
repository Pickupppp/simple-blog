package com.pickup.blog.shiro;

import com.pickup.blog.entity.User;
import com.pickup.blog.service.UserService;
import com.pickup.blog.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashSet;

@Component
@Slf4j
public class AccountRealm extends AuthorizingRealm {

    @Resource
    UserService userService;
    @Resource
    JwtUtil jwtUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 鉴权还没搞，暂时都设置 admin 权限
////        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
////        User user = (User) primaryPrincipal;
//        SimpleAuthorizationInfo userInfo = new SimpleAuthorizationInfo();
//        userInfo.setRoles(new HashSet<String>() {{
//            add("admin");
//        }});
//        return userInfo;
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken token = (JwtToken) authenticationToken;
        log.info("Accept token is {}", token);
        String userId = jwtUtil.getClaimByToken((String) token.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userId));

        if (user == null) {
            throw new UnknownAccountException("该用户不存在！");
        }
        if (user.getStatus() == 0) {
            throw new LockedAccountException("该用户被冻结！");
        }
        return new SimpleAuthenticationInfo(user, token.getCredentials(), getName());
    }
}
