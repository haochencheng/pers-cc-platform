package pers.cc.blog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pers.cc.blog.model.Blogger;
import pers.cc.blog.service.BloggerService;
import pers.cc.common.utils.CryptographyUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 博主Controller层
 * 
 * @author Administrator
 *
 */
@Controller
public class BloggerController {

    @Resource
    private BloggerService bloggerService;

    @RequestMapping("/cc/login.html")
    public String login(Blogger blogger, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:/cc/main.jsp";
        }
        String userName = blogger.getUserName();
        String password = blogger.getPassword();
        if (blogger == null || pers.cc.common.utils.StringUtil.isEmpty(userName)
                || pers.cc.common.utils.StringUtil.isEmpty(password)) {
            return "/cc/login";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userName,
                CryptographyUtil.md5(password, "cc"));
        try {
            subject.login(token); // 登录验证
            return "redirect:/cc/main.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("blogger", blogger);
            request.setAttribute("errorInfo", "用户名或密码错误");
            return "/cc/login";
        }
    }

    /**
     * 关于博主
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/blogger/aboutMe.html")
    public ModelAndView aboutMe() throws Exception {
        ModelAndView mAndView = new ModelAndView();
        mAndView.addObject("pageTitle", "关于博主_cc个人博客系统");
        mAndView.addObject("mainPage", "foreground/blogger/info.jsp");
        mAndView.setViewName("mainTemp");
        return mAndView;
    }

}
