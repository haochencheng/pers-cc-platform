package pers.platform.core.mail.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.MessagingException;
import pers.platform.common.base.BaseResult;
import pers.platform.common.enums.BaseResultStatusEnum;
import pers.platform.common.enums.EmailTypeEnum;
import pers.platform.core.mail.model.EmailEntity;
import pers.platform.core.mail.api.SendEmailService;


@Service
public class SendEmailServiceImpl implements SendEmailService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender sender;

    @Override
    public BaseResult sendEmailSync(Subject subject, EmailEntity emailEntity) {
        BaseResult baseResult=new BaseResult();
        switch (EmailTypeEnum.valueOf(emailEntity.getType())){
            case TEXT:
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("");
                message.setTo(emailEntity.getTo());
                message.setSubject(emailEntity.getSubject());
                message.setText(emailEntity.getText());
                try {
                        sender.send(message);
                        logger.info("html邮件已经发送。");
                        baseResult.setStatus(BaseResultStatusEnum.SUCCESS.getCode());
                } catch (MessagingException e) {
                    baseResult.setStatus(BaseResultStatusEnum.FAIL.getCode());
                    baseResult.setMessage("发送html邮件时发生异常！");
                }
                return baseResult;
        case HTML:
            case FILE:
            default:

        }
        return baseResult;
    }

    @Override
    public void sendEmailASync(Subject subject,EmailEntity emailEntity) {

    }
}
