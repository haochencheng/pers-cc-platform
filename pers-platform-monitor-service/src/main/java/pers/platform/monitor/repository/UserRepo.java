package pers.platform.monitor.repository;

import java.io.Serializable;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pers.platform.monitor.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Serializable>  {

    User getUserByUserNameOrPhoneOrEmail(String userName, String phone,
            String email);

}
