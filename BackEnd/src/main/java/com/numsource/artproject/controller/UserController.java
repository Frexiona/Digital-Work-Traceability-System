package com.numsource.artproject.controller;

import com.numsource.artproject.DTO.UserRegisterForm;
import com.numsource.artproject.entity.User;
import com.numsource.artproject.enums.ResultEnum;
import com.numsource.artproject.exception.MyException;
import com.numsource.artproject.service.RpcService;
import com.numsource.artproject.service.UserService;
import com.numsource.artproject.service.redis.RedisService;
import com.numsource.artproject.util.EmailSend;
import com.numsource.artproject.util.MyUtil;
import com.numsource.artproject.util.ResultVOUtil;
import com.numsource.artproject.util.convert.UserConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RpcService rpcService;

    @RequestMapping("/register")
    public Object register(@Valid UserRegisterForm userRegisterForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new MyException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        String key = redisService.getKey(userRegisterForm.getEmailAddress());
        log.info("key:{},userRegisterForm.getCode:{}",key,userRegisterForm.getCode());
        if(userRegisterForm.getCode().equals(key)){
            User user = UserConvert.convertUser(userRegisterForm);
            user.setRole("user");//默认是指权限为普通用户
            //TODO 创建账户需要写入区块链
            try {
                user.setAddress(rpcService.createUser(user.getPassword()).toString());
            } catch (Throwable throwable) {
                return ResultVOUtil.error(ResultEnum.CREATE_USER_ADDRESS_FAIL);
            }
            User userResult = new User();
            try {
                userResult = userService.addUser(user);
            }catch (MyException e){
                return ResultVOUtil.error(ResultEnum.EMAIL_REGISTED);
            }
            return ResultVOUtil.success(userResult);
        }
        return ResultVOUtil.error(0,"验证码错误");
    }

    /**
     * 发送邮箱验证码
     * @return
     */
    @RequestMapping("/sendCode")
    public Object sendCode(@RequestParam(value = "emailAddress",required = true) String sendAddress){
        String code = MyUtil.getRandomStr();
        try {
            EmailSend.sendMsgByMyEmail(code,sendAddress);
        } catch (Exception e) {
            log.error("邮件发送失败..");
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SEND_EMAIL_FAIL);
        }
        redisService.setObject(sendAddress,code);
        return ResultVOUtil.success();
    }

}
