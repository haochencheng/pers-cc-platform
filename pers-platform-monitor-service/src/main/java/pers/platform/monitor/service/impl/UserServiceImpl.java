package pers.platform.monitor.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pers.platform.monitor.model.User;
import pers.platform.monitor.repository.UserRepo;
import pers.platform.monitor.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserRepo userRepo;

    @Override
    public User getUserById(long id) {
        return userRepo.findOne(id);
    }

    @Override
    public User getUserByIdAndName(String userName, String password) {
        return userRepo.findUserByUserNameAndPassword(userName, password);
    }

}
