package pers.platform.demo.account.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pers.platform.demo.account.model.Account;

import java.io.Serializable;

@Repository
public interface AccountRepo extends CrudRepository<Account, Serializable>  {


    /**
     * 根据userId获取用户账户信息
     *
     * @param userId 用户id
     * @return AccountDO
     */
    Account findByUserId(String userId);

    /**
     * 扣减账户余额
     *
     * @param account 实体类
     * @return rows
     */
    @Query(value = "update account set balance =:#{#account.balance}," +
            " freeze_amount= :#{#account.freezeAmount} ,update_time = :#{#account.updateTime}" +
            " where user_id =:#{#account.userId}  and  balance > 0  ",nativeQuery = true)
    @Modifying
    int update(@Param("account") Account account);




    /**
     * 确认扣减账户余额
     *
     * @param account 实体类
     * @return rows
     */
    @Query(value = "update account set " +
            " freeze_amount= :#{#account.freezeAmount} ,update_time = :#{#account.updateTime}" +
            " where user_id =:#{#account.userId}  and freeze_amount >0 ",nativeQuery = true)
    @Modifying
    int confirm(@Param("account") Account account);



    /**
     * 取消扣减账户余额
     *
     * @param account 实体类
     * @return rows
     */
    @Query(value="update account set balance =:#{#account.balance}," +
            " freeze_amount= :#{#account.freezeAmount} ,update_time = :#{#account.updateTime}" +
            " where user_id =:#{#account.userId}  and freeze_amount >0",nativeQuery = true)
    @Modifying
    int cancel(@Param("account") Account account);

}
