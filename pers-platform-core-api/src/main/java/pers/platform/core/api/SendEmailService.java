package pers.platform.core.api;


import pers.platform.core.model.EmailEntity;
import pers.platform.common.base.BaseResult;

/**
 * @author cc
 * @date 2018-01-14
 */
public interface SendEmailService {

    //同步调用
    BaseResult sendEmailSync(String apiKey,String apiSecret, EmailEntity emailEntity);

    //异步调用
    void sendEmailASync(String apiKey, String apiSecret, EmailEntity emailEntity);

}
