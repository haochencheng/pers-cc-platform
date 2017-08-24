package pers.monitor.platform.repository;

import org.springframework.data.repository.CrudRepository;
import pers.monitor.platform.model.User;

import java.io.Serializable;

public interface UserRepo extends CrudRepository<User, Serializable> {

    User findUserByUserNameAndPassword(String userName, String password);

}
