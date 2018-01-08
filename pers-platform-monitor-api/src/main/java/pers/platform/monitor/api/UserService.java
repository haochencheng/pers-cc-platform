package pers.platform.monitor.api;

import pers.platform.monitor.model.User;

public interface UserService {

    User getUserById(long id);

    User getUserByUserNameOrPhoneOrEmail(String userName, String phone,
                                         String email);
}
