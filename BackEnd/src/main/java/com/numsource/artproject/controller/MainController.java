package com.numsource.artproject.controller;

import com.numsource.artproject.DTO.PictureForm;
import com.numsource.artproject.DTO.PictureInfo;
import com.numsource.artproject.entity.User;
import com.numsource.artproject.enums.ResultEnum;
import com.numsource.artproject.exception.MyException;
import com.numsource.artproject.service.ProductService;
import com.numsource.artproject.service.RpcService;
import com.numsource.artproject.service.UserService;
import com.numsource.artproject.util.ResultVOUtil;
import com.numsource.artproject.util.dataUtil.CommandUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.DenyAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.io.File;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@CrossOrigin
@Slf4j
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/test")
    public Object test(){
        String str = CommandUtil.doCmd("cinstraceability.getCommodityCode('test')");
        Map map = new HashMap();
        map.put("result",str);
        return map;
    }

    @RequestMapping("/")
    public Object view(){
        log.info("success.return");
        return ResultVOUtil.success();
    }

    @RequestMapping("/index")
    public Object firstView(){
        log.info("success.return..firstView");
        return ResultVOUtil.success();
    }

//    @RequestMapping("/login")
//    public Object login(){
//        return ResultVOUtil.success();
//    }

    @RequestMapping("/login")
    public Object login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // rememberme
            token.setRememberMe(true);
            try {
                System.out.println("1. " + token.hashCode());
                // 执行登录.
                currentUser.login(token);
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            // 所有认证时异常的父类.
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
                System.out.println("登录失败: " + ae.getMessage());
                if (ae instanceof UnknownAccountException){
                    return ResultVOUtil.error(ResultEnum.NOT_EXIST.getCode(),
                            ResultEnum.NOT_EXIST.getMsg());
                }else {
                    return ResultVOUtil.error(ResultEnum.PASSWORD_WRONG.getCode(),
                            ResultEnum.PASSWORD_WRONG.getMsg());
                }
            }/*catch (AuthenticationException ae){
                return ResultVOUtil.error(ResultEnum.PASSWORD_WRONG.getCode(),
                        ResultEnum.PASSWORD_WRONG.getMsg());
            }*/
        }

        return ResultVOUtil.success();
    }

//    @RequestMapping("/403")
//    public String unauthorizedRole(){
//        System.out.println("------没有权限-------");
//        return "403";
//    }



    @RequestMapping("/upload")
    public Object singleFileUpload(@Valid PictureForm pictureForm,
                                   BindingResult bindingResult,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        if (bindingResult.hasErrors()){
            log.error(bindingResult.getFieldError().getDefaultMessage());
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR);
        }
        String sessionId = request.getSession().getId();
        User user = userService.getCurrentUser(sessionId,request,response);
        return productService.uploadProduct(pictureForm,user);
    }


    @RequestMapping("/getProductInfo")
    public Object getProductInfo(String productId){
        //根据参数查询得到数据库信息,再根据用户的Id查询得到用户的信息在方法中传入用户的address
//        rpcService.getAllProductInfo(productId,)
        return null;
    }

    @RequestMapping("/allImg")
    public Object getAllPictures(){
        //getAllPics()....
        List<PictureInfo> pictureInfos= new ArrayList<>();
        PictureInfo pictureInfo = new PictureInfo();
        pictureInfo.setPicture("/home/hk/图片/pic1.jpg");
        pictureInfo.setAuthor("clf");
        pictureInfo.setName("蒙娜丽莎");
        pictureInfo.setPrice(12000);
        pictureInfo.setInfo("杰作");
        pictureInfo.setHot(56);
        pictureInfo.setOwner("clf");
        pictureInfo.setStatus("立即出售");
        pictureInfos.add(pictureInfo);
        pictureInfos.add(pictureInfo);
        pictureInfos.add(pictureInfo);
        return ResultVOUtil.success(pictureInfos);
    }



}
