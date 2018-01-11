/*
 *
 * Copyright 2017-2018 549477611@qq.com(xiaoyu)
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, see <http://www.gnu.org/licenses/>.
 *
 */

package pers.platform.account.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.happylifeplat.tcc.annotation.Tcc;
import com.happylifeplat.tcc.common.exception.TccRuntimeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pers.platform.account.repository.AccountRepo;
import pers.platform.demo.account.dto.AccountDTO;
import pers.platform.demo.account.entity.Account;
import pers.platform.demo.account.api.AccountService;

import java.util.Date;

/**
 * @author xiaoyu
 */
@Service
public class AccountServiceImpl implements AccountService {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepo accountRepo;


    /**
     * 扣款支付
     *
     * @param accountDTO 参数dto
     * @return true
     */
    @Override
    @Tcc(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean payment(AccountDTO accountDTO) {
        final Account account = accountRepo.findByUserId(accountDTO.getUserId());
        account.setBalance(account.getBalance().subtract(accountDTO.getAmount()));
        account.setFreezeAmount(account.getFreezeAmount().add(accountDTO.getAmount()));
        account.setUpdateTime(new Date());
        final int update= accountRepo.update(account);
        if (update != 1) {
            throw new TccRuntimeException("资金不足！");
        }
        return Boolean.TRUE;
    }

    /**
     * 获取用户账户信息
     *
     * @param userId 用户id
     * @return AccountDO
     */
    @Override
    public Account findByUserId(String userId) {
        return accountRepo.findByUserId(userId);
    }

    public boolean confirm(AccountDTO accountDTO) {

        LOGGER.debug("============dubbo tcc 执行确认付款接口===============");

        final Account accountDO = accountRepo.findByUserId(accountDTO.getUserId());
        accountDO.setFreezeAmount(accountDO.getFreezeAmount().subtract(accountDTO.getAmount()));
        accountDO.setUpdateTime(new Date());
        final int rows = accountRepo.confirm(accountDO);
        if(rows!=1){
            throw  new TccRuntimeException("确认扣减账户异常！");
        }
        return Boolean.TRUE;
    }


    public boolean cancel(AccountDTO accountDTO) {

        LOGGER.debug("============ dubbo tcc 执行取消付款接口===============");
        final Account accountDO = accountRepo.findByUserId(accountDTO.getUserId());
        accountDO.setBalance(accountDO.getBalance().add(accountDTO.getAmount()));
        accountDO.setFreezeAmount(accountDO.getFreezeAmount().subtract(accountDTO.getAmount()));
        accountDO.setUpdateTime(new Date());
        final int rows = accountRepo.cancel(accountDO);
        if(rows!=1){
            throw  new TccRuntimeException("取消扣减账户异常！");
        }
        return Boolean.TRUE;
    }
}
