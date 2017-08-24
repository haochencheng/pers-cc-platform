package pers.monitor.platform.service.impl;

import org.springframework.stereotype.Service;
import pers.monitor.platform.model.User;
import pers.monitor.platform.repository.UserRepo;
import pers.monitor.platform.service.UserService;

import javax.annotation.Resource;

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
