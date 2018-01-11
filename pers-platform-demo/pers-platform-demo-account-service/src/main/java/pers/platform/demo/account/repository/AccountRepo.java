package pers.platform.demo.account.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
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
    @Query(value = "update account set balance =#{balance}," +
            " freeze_amount= #{freezeAmount} ,update_time = #{updateTime}" +
            " where user_id =#{userId}  and  balance > 0  ",nativeQuery = true)
    int update(Account account);




    /**
     * 确认扣减账户余额
     *
     * @param account 实体类
     * @return rows
     */
    @Query(value = "update account set " +
            " freeze_amount= #{freezeAmount} ,update_time = #{updateTime}" +
            " where user_id =#{userId}  and freeze_amount >0 ",nativeQuery = true)
    int confirm(Account account);



    /**
     * 取消扣减账户余额
     *
     * @param account 实体类
     * @return rows
     */
    @Query(value="update account set balance =#{balance}," +
            " freeze_amount= #{freezeAmount} ,update_time = #{updateTime}" +
            " where user_id =#{userId}  and freeze_amount >0",nativeQuery = true)
    int cancel(Account account);

}
