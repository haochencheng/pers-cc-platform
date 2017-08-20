package pers.monitor.platform.controller.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.monitor.platform.model.User;
import pers.monitor.platform.service.UserService;

@RestController
public class UserApi {

    @Resource
    UserService userServuce;

    @RequestMapping(value = "/api/users")
    public User getAll() {
        return userServuce.getUserById(1);
    }

}
