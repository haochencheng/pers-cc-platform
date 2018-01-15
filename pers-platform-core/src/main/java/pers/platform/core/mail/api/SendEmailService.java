package pers.platform.core.mail.api;


import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.subject.Subject;
import pers.platform.common.base.BaseResult;
import pers.platform.core.mail.model.EmailEntity;

/**
 * @author cc
 * @date 2018-01-14
 */
public interface SendEmailService {

    //同步调用
    BaseResult sendEmailSync(Subject subject, EmailEntity emailEntity);

    //异步调用
    void sendEmailASync(Subject subject,EmailEntity emailEntity);

}
