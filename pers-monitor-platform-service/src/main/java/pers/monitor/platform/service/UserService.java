package pers.monitor.platform.service;

import pers.monitor.platform.model.User;

public interface UserService {

    User getUserById(long id);

    User getUserByIdAndName(String userName, String password);
}
