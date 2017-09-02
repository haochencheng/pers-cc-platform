package pers.platform.monitor.controller.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.platform.monitor.model.User;

@RestController
public class UserApi {

    @Resource
    pers.platform.monitor.service.UserService userServuce;

    @RequestMapping(value = "/api/users")
    public User getAll() {
        return userServuce.getUserById(1);
    }

}
