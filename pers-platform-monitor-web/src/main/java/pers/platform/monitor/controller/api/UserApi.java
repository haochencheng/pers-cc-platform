package pers.platform.monitor.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.platform.monitor.model.User;

import javax.annotation.Resource;

@RestController
public class UserApi {

    @Resource
    pers.platform.monitor.service.UserService userServuce;

    @RequestMapping(value = "/api/users")
    public User getAll() {
        return userServuce.getUserById(1);
    }

}
