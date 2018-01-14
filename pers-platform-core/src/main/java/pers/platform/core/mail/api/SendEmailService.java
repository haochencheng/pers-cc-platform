package pers.platform.core.mail.api;


import com.alibaba.fastjson.JSONObject;
import pers.platform.core.mail.model.EmailEntity;

/**
 * @author cc
 * @date 2018-01-14
 */
public interface SendEmailService {

    //同步调用
    JSONObject sendEmailSync(EmailEntity emailEntity);

    //异步调用
    void sendEmailASync();

}
