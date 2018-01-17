package pers.platform.monitor.service.impl;

import javax.annotation.Resource;


import com.alibaba.dubbo.config.annotation.Service;
import pers.platform.monitor.api.UserService;
import pers.platform.monitor.model.User;
import pers.platform.monitor.repository.UserRepo;

// 注册为 Dubbo 服务
@Service
@org.springframework.stereotype.Service
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
