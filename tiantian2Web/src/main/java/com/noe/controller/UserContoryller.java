package com.noe.controller;

import com.noe.pojo.User;
import com.noe.service.UserService;
import com.noe.utils.MyStatus;
import com.sun.org.apache.xalan.internal.xsltc.runtime.StringValueHandler;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:nore
 */
@RequestMapping("/user")
@Controller
public class UserContoryller {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public MyStatus userLogin(@RequestBody User user){
        System.out.println("接收到的数据为: "+user.toString());
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        token.setRememberMe(true);
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            return MyStatus.error("登录时失败");
        }
        return MyStatus.ok("登录成功");
    }

    @RequiresRoles("admin")
    @RequestMapping("/admin")
    public String admin(){
        return "WEB-INF/admin";
    }
}
