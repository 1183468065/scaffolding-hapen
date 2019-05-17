package com.example.example.controller;

import com.example.example.model.User;
import com.example.example.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * created by CaiBaoHong at 2018/4/17 16:41<br>
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 登录接口，由于UserService中是模拟返回用户信息的，
     * 所以用户名随意，密码123456
     *
     * @return
     */
    @GetMapping("/login")
    @ResponseBody
    public Object login(String username, String pwd) {

        User userDB = userService.findUserByName(username);
        Subject currentUser = SecurityUtils.getSubject();
        //登录
        currentUser.login(new UsernamePasswordToken(userDB.getUname(), userDB.getPwd()));
        //从session取出用户信息
        User user = (User) currentUser.getPrincipal();
        if (user == null) throw new AuthenticationException();
        //返回登录用户的信息给前台，含用户的所有角色和权限
        return user;
    }


}
