package pers.platform.core.mail.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.platform.common.base.BaseResult;
import pers.platform.core.mail.api.SendEmailService;
import pers.platform.core.mail.model.EmailEntity;

import javax.validation.Valid;

/**
 * Created by cc on  2018/1/16
 */
@RestController
@RequestMapping("/mail")
public class MailController {
    
    
    @Autowired
    private SendEmailService sendEmailService;
    
    @PostMapping("/sync")
    @ResponseBody
    @ApiOperation(value = "同步发送Emali接口")
    @ApiImplicitParam(name = "emailEntity", value = "邮件实体", required = true, dataType = "EmailEntity")
    public BaseResult sendEmailSync(@Validated @RequestBody String apiKey,String apiSercuty, EmailEntity emailEntity){
        return sendEmailService.sendEmailSync(apiKey,apiSercuty,emailEntity);
    }
    
}
