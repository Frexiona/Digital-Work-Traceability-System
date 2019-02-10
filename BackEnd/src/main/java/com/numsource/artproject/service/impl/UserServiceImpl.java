package com.numsource.artproject.service.impl;

import com.numsource.artproject.dao.UserRepository;
import com.numsource.artproject.entity.User;
import com.numsource.artproject.exception.MyException;
import com.numsource.artproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(User user) {
        if(userRepository.findByEmailAddress(user.getEmailAddress())==null){
            return userRepository.save(user);
        }
        throw new MyException(0,"该邮箱已被注册");
    }

    //得到当前请求的用户信息
    @Override
    public User getCurrentUser(String sessionID, HttpServletRequest request, HttpServletResponse response) {
        SessionKey key = new WebSessionKey(sessionID,request,response);
        try{
            Session se = SecurityUtils.getSecurityManager().getSession(key);
            Object obj = se.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
            String userEmail = String.valueOf(coll.getPrimaryPrincipal());
            return userRepository.findByEmailAddress(userEmail);
        }catch(Exception e){
            log.error("获取当前用户信息异常..");
            e.printStackTrace();
        }finally{
        }
        return null;
    }


}
