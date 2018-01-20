package pers.platform.core.auth.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;
import pers.platform.core.auth.model.ApiAccessCounter;

import java.io.Serializable;

@Repository
public interface ApiAccessCounterRepo extends CrudRepository<ApiAccessCounter,Serializable> {


    @Modifying
    @Query(value="INSERT INTO `api_request_count` (`api_user_auth_Id`, `pond`, `api_request_count`) VALUES (#{apiUserAuthId}, RAND()*10, 1) ON DUPLICATE KEY UPDATE `api_request_count`=`api_request_count`+1",nativeQuery=true)
    int incrementApiCount(@Param("apiUserAuthId") String apiUserAuthId);


}
