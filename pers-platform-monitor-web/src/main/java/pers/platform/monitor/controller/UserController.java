package pers.platform.monitor.controller;

import javax.annotation.Resource;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pers.platform.monitor.model.User;
import pers.platform.monitor.service.UserService;
import pers.platfrom.common.utils.CryptographyUtil;

@Controller
public class UserController {

    @Resource
    private Environment env;

    @Resource
    UserService userServuce;

    @RequestMapping(value = "/mainPage.html")
    public ModelAndView login(User user) {
        ModelAndView modelAndView = new ModelAndView();
        User user2 = userServuce.getUserByIdAndName(user.getUserName(),
                CryptographyUtil.md5(user.getPassword(),
                        env.getProperty("salt")));
        modelAndView.addObject("message", "helloworld");
        if (user2 != null) {
            modelAndView.setViewName("/admin/mainPage");
        } else {
            modelAndView.setViewName("/index");
        }
        return modelAndView;
    }

}
