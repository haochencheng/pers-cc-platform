package pers.platform.core.mail.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import pers.platform.core.mail.model.EmailEntity;
import pers.platform.core.mail.api.SendEmailService;

@Service
public class SendEmailServiceImpl implements SendEmailService{


    @Override
    public JSONObject sendEmailSync(EmailEntity emailEntity) {
        return null;
    }

    @Override
    public void sendEmailASync() {

    }
}
