package pers.platform.account.repository;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pers.platform.demo.account.entity.Account;

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
    @SQLUpdate(sql = "update account set balance =#{balance}," +
            " freeze_amount= #{freezeAmount} ,update_time = #{updateTime}" +
            " where user_id =#{userId}  and  balance > 0  ")
    int update(Account account);




    /**
     * 确认扣减账户余额
     *
     * @param account 实体类
     * @return rows
     */
    @SQLUpdate(sql = "update account set " +
            " freeze_amount= #{freezeAmount} ,update_time = #{updateTime}" +
            " where user_id =#{userId}  and freeze_amount >0 ")
    int confirm(Account account);



    /**
     * 取消扣减账户余额
     *
     * @param account 实体类
     * @return rows
     */
    @SQLUpdate(sql="update account set balance =#{balance}," +
            " freeze_amount= #{freezeAmount} ,update_time = #{updateTime}" +
            " where user_id =#{userId}  and freeze_amount >0")
    int cancel(Account account);

}
