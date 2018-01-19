package pers.platform.core.mail.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.juli.FileHandler;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import pers.platform.common.base.BaseResult;
import pers.platform.common.enums.BaseResultStatusEnum;
import pers.platform.common.enums.EmailTypeEnum;
import pers.platform.common.utils.common.StringUtil;
import pers.platform.common.utils.file.FileBytesConvertUtil;
import pers.platform.core.mail.model.EmailEntity;
import pers.platform.core.mail.api.SendEmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class SendEmailServiceImpl implements SendEmailService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender sender;

    @Override
    public BaseResult sendEmailSync(String apiKey,String apiSecret, EmailEntity emailEntity) {
        BaseResult baseResult=new BaseResult();
        switch (EmailTypeEnum.valueOf(emailEntity.getType())){
            case TEXT:
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(StringUtil.isEmpty(emailEntity.getFrom())?from:emailEntity.getFrom());
                message.setTo(emailEntity.getTo());
                message.setSubject(emailEntity.getSubject());
                message.setText(emailEntity.getText());
                try {
                    sender.send(message);
                    baseResult.setStatus(BaseResultStatusEnum.SUCCESS.getCode());
                } catch (Exception e) {
                    baseResult.setMessage("发送html邮件时发生异常！");
                    e.printStackTrace();
                }
                break;
            case HTML:
                MimeMessage mimeMessage=sender.createMimeMessage();
                try {
                    //true表示需要创建一个multipart message
                    MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
                    helper.setFrom(StringUtil.isEmpty(emailEntity.getFrom())?from:emailEntity.getFrom());
                    helper.setTo(emailEntity.getTo());
                    helper.setSubject(emailEntity.getSubject());
                    helper.setText(emailEntity.getText());
                    sender.send(mimeMessage);
                }catch (MessagingException e) {
                    baseResult.setMessage("发送邮件时发生异常！");
                    e.printStackTrace();
                }
                break;
            case FILE:
                MimeMessage mimeMessageFile=sender.createMimeMessage();
                MimeMessageHelper helper= null;
                try {
                    helper = new MimeMessageHelper(mimeMessageFile,true);
                    helper.setFrom(StringUtil.isEmpty(emailEntity.getFrom())?from:emailEntity.getFrom());
                    helper.setTo(emailEntity.getTo());
                    helper.setSubject(emailEntity.getSubject());
                    helper.setText(emailEntity.getText());
                    FileSystemResource fileSystemResource=new FileSystemResource(FileBytesConvertUtil.bytes2file(emailEntity.getFile()));
                    helper.addAttachment(emailEntity.getFileName(),fileSystemResource);
                    sender.send(mimeMessageFile);
                } catch (MessagingException e) {
                    baseResult.setMessage("发送邮件时发生异常！");
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case RESOURCE:
                MimeMessage mimeMessageResource=sender.createMimeMessage();
                MimeMessageHelper resourceHelper= null;
                try {
                    resourceHelper = new MimeMessageHelper(mimeMessageResource,true);
                    resourceHelper.setFrom(StringUtil.isEmpty(emailEntity.getFrom())?from:emailEntity.getFrom());
                    resourceHelper.setTo(emailEntity.getTo());
                    resourceHelper.setSubject(emailEntity.getSubject());
                    resourceHelper.setText(emailEntity.getText());
                    FileSystemResource fileSystemResource=new FileSystemResource(FileBytesConvertUtil.bytes2file(emailEntity.getFile()));
                    resourceHelper.addInline(emailEntity.getFileName(),fileSystemResource);
                    sender.send(mimeMessageResource);
                }catch (Exception e) {
                    e.printStackTrace();
                    baseResult.setMessage("发送邮件时发生异常！");
                }
            default:
                baseResult.setStatus(BaseResultStatusEnum.FAIL.getCode());
                baseResult.setMessage("输入参数有误，请检查type字段");
        }
        baseResult.setMessage("邮件发送成功！");
        return baseResult;
    }

    @Override
    public void sendEmailASync(String apiKey,String apiSecret,EmailEntity emailEntity) {

    }
}
