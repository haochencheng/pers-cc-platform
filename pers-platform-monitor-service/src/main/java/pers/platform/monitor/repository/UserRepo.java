package pers.platform.monitor.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import pers.platform.monitor.model.User;

public interface UserRepo extends CrudRepository<User, Serializable> {

    User findUserByUserNameAndPassword(String userName, String password);

}
