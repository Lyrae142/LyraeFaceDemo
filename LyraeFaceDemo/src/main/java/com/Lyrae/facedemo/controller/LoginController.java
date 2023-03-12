package com.Lyrae.facedemo.controller;

import com.Lyrae.facedemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @Author:Lyrae
 */
@Controller
@Slf4j
public class LoginController {

    //将Service注入到Web层
    @Resource
    UserService userService;

    /**
     * 实现登录操作
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 创建Subject实例,获取当前登录用户的信息
        Subject currentUser = SecurityUtils.getSubject();

        // 将用户名及密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            currentUser.login(token);
            // 判断当前用户是否登录,身份认证
            if (currentUser.isAuthenticated() == true) {
                log.info("登录成功");
                return "success";
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            log.info("输入的账号名或密码错误");
        }
        return "login";
    }

    /**
     * 实现注册操作
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/doRegister",method = RequestMethod.POST)
    public String doRegister(@RequestParam("username") String username,@RequestParam("password") String password){

        boolean result = userService.registerData(username,password);
        if(result){
            log.info("成功注册");
            return "login";
        }
        log.info("注册失败");
        return "/register";
    }

    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = "/login")
    public String login() {
        log.info("login() 方法被调用");
        return "login";
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping(value = "/register")
    public String register() {
        log.info("register() 方法被调用");
        return "register";
    }

}
