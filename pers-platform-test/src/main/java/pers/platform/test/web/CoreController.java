package pers.platform.test.web;


import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.platform.common.base.BaseResult;
import pers.platform.core.api.SendEmailService;
import pers.platform.core.model.EmailEntity;


@RestController
@RequestMapping("/core")
public class CoreController {

    @Reference
    private SendEmailService sendEmailService;


    @PostMapping(value = "/send/sync")
    @ApiOperation(value = "发送Email接口（注意这里模拟的发送Email操作操作）")
    public BaseResult sendEmailSync(@RequestParam(value = "apiKey") String apiKey,
                                    @RequestParam(value = "apiSercuty") String apiSercuty, EmailEntity emailEntity) {

        return sendEmailService.sendEmailSync(apiKey, apiSercuty,emailEntity);

    }

}
