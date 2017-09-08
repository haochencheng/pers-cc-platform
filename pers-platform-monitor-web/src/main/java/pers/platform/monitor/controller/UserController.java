package pers.platform.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pers.platform.monitor.model.User;
import pers.platform.monitor.service.UserService;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    UserService userServuce;

    // @RequestMapping(value = "/login.html", method = RequestMethod.POST,
    // consumes = "application/json")
    @RequestMapping(value = "/login.html")
    public ModelAndView login(User user) {
        ModelAndView modelAndView = new ModelAndView();
        User user2 = userServuce.getUserByIdAndName(user.getUserName(),
                user.getPassword());
        modelAndView.addObject("message", "helloworld");
        if (user2 != null) {
            modelAndView.setViewName("/admin/mainPage");
        } else {
            modelAndView.setViewName("/index");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/register.html")
    public String register() {
        return "register";
    }

}
