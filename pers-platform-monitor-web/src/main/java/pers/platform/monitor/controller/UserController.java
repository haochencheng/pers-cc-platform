package pers.platform.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.platform.monitor.model.User;
import pers.platform.monitor.service.UserService;
import pers.platfrom.common.utils.StringUtil;

@Controller
public class UserController {

    @Resource
    UserService userServuce;

    @RequestMapping(value = "/mainPage.html")
    public String login(User user, HttpServletRequest request,
            HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        String userName = user.getUserName();
        String password = user.getPassword();
        // 过滤特殊字符
        if (StringUtil.filterSpecialChar(password)) {
            request.setAttribute("user", user);
            request.setAttribute("errorInfo", "密码不能含有特殊字符!");
            return "index";
        }
        // 默认记住我
        UsernamePasswordToken userNametoken = new UsernamePasswordToken(
                userName, password, false);
        String error = null;
        try {
            subject.login(userNametoken); // 登录验证
            request.setAttribute("message", "helloworld");
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (ExcessiveAttemptsException e) {
            error = "登录失败5次，账户锁定10分钟";
        } catch (AuthenticationException e) {
            // 其他错误，比如锁定，如果想单独处理请单独catch处理
            error = "其他错误：" + e.getMessage();
        }
        if (error != null) {
            // 出错了，返回登录页面
            request.setAttribute("error", error);
            return "index";
        } else {
            // 登录成功
            return "redirect:/admin/mainPage";
        }
    }

}
