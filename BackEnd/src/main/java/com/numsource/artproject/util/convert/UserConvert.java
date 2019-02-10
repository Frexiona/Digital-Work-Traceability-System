package com.numsource.artproject.util.convert;

import com.numsource.artproject.DTO.UserRegisterForm;
import com.numsource.artproject.entity.User;
import org.apache.shiro.crypto.hash.Md5Hash;

public class UserConvert {

    //将表单的用户信息转换为用户类
    public static User convertUser(UserRegisterForm form){
        User user = new User();
        user.setEmailAddress(form.getEmailAddress());
        Md5Hash md5Hash = new Md5Hash(form.getPassword(),form.getEmailAddress());
        user.setPassword(md5Hash.toString());
        return user;
    }
}
