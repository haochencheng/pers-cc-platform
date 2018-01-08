package pers.platform.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.platform.common.utils.StringUtil;
import pers.platform.monitor.api.UserService;
import pers.platform.monitor.model.User;

@Controller
public class UserController {

    @Resource
    UserService userServuce;

    @RequestMapping(value = "/login.html")
    public String login(User user, HttpServletRequest request,
            HttpServletResponse response) throws LockedAccountException {
        Subject subject = SecurityUtils.getSubject();
        String userName = user.getPrincipal();
        String password = String.valueOf(user.getPassword());
        if (StringUtil.isEmpty(userName)) {
            throw new IncorrectCredentialsException("username is null!");
        } else if (StringUtil.isEmpty(password)) {
            throw new IncorrectCredentialsException("password is null!");
        }
        // 过滤特殊字符
        if (StringUtil.filterSpecialChar(password)) {
            request.setAttribute("user", user);
            request.setAttribute("error", "密码不能含有特殊字符!");
            return "index";
        }
        String error = null;
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken userNametoken;
            if (user.isRememberMe()) {
                userNametoken = new UsernamePasswordToken(userName, password,
                        true);
            } else {
                userNametoken = new UsernamePasswordToken(userName, password,
                        false);
            }
            try {
                subject.login(userNametoken); // 登录验证
                request.setAttribute("message", "helloworld");
            } catch (UnknownAccountException
                    | IncorrectCredentialsException e) {
                error = "用户名或密码错误";
            } catch (AuthenticationException e) {
                // 其他错误，比如锁定，如果想单独处理请单独catch处理
                error = "登录失败5次，账户锁定10分钟";
            }
        }
        // 默认记住我
        if (error != null) {
            // 出错了，返回登录页面
            request.setAttribute("error", error);
            return "index";
        } else {
            // 登录成功
            return "redirect:/admin/mainPage";
        }
    }

    @RequestMapping("/logout")
    public void logout() {
        SecurityUtils.getSubject().logout();
    }

}
