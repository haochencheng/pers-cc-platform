package pers.platform.core.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pers.platform.core.auth.model.ApiUserAuth;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ApiUserAuthRepo extends CrudRepository<ApiUserAuth, Serializable> {


    ApiUserAuth getAllByApiKeyAndApiSecret(String apiKey,String apiSecret);

}
