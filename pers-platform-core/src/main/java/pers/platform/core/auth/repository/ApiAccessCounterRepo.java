package pers.platform.core.auth.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import pers.platform.core.auth.model.ApiAccessCounter;

import java.io.Serializable;

@Repository
public interface ApiAccessCounterRepo extends CrudRepository<ApiAccessCounter,Serializable> {


    @Modifying
    @Transactional
    @Query(value="INSERT INTO `api_access_counter` (`api_user_auth_Id`, `pond`, `api_request_count`) VALUES (?1, RAND()*10, 1) ON DUPLICATE KEY UPDATE `api_request_count`=`api_request_count`+1",nativeQuery=true)
    int incrementApiCount(String apiUserAuthId);


}
