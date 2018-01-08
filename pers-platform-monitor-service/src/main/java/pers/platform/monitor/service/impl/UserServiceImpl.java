package pers.platform.monitor.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pers.platform.monitor.api.UserService;
import pers.platform.monitor.model.User;
import pers.platform.monitor.repository.UserRepo;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserRepo userRepo;

    @Override
    public User getUserById(long id) {
        return userRepo.findOne(id);
    }

    @Override
    public User getUserByUserNameOrPhoneOrEmail(String userName, String phone,
            String email) {
        return userRepo.getUserByUserNameOrPhoneOrEmail(userName, phone, email);
    }

}
