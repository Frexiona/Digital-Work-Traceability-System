package com.numsource.artproject.config;

import com.numsource.artproject.dao.UserRepository;
import com.numsource.artproject.entity.User;
import com.numsource.artproject.enums.ResultEnum;
import com.numsource.artproject.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = getRolesByUserName(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);

        return simpleAuthorizationInfo;
    }

    private Set<String> getRolesByUserName(String username) {
        Set<String> roles = new HashSet<>();
        roles.add(userRepository.findByEmailAddress(username).getRole());
        return roles;
    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.从主体传过来的认证信息中,获得用户名
        String username = (String) authenticationToken.getPrincipal();


        //2.通过用户名从数据库获取凭证
        String  password = getPasswordByUsername(username);

        log.info("password:{}",password);
        if(password == null){
            log.info("用户信息不存在...");
            throw new UnknownAccountException();
        }
        //若密码不为空说明用户存在
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password,"customRealm");

        ByteSource x = ByteSource.Util.bytes(username);
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        return authenticationInfo;
    }

    private String getPasswordByUsername(String username) {
        log.info("username:{}",username);
        log.info("getPassword....");
        User user = userRepository.findByEmailAddress(username);
        if (user==null){
            return null;
        }
        String password = user.getPassword();
        return password;
    }
}
