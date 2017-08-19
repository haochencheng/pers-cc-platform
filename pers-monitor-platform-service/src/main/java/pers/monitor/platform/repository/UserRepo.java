package pers.monitor.platform.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import pers.monitor.platform.model.User;

public interface UserRepo extends CrudRepository<User, Serializable> {

    User findUserByUserNameAndPassword(String userName, String password);

}
