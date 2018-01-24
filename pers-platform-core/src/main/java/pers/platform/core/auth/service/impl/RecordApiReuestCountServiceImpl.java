package pers.platform.core.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import pers.platform.core.auth.service.RecordApiReuestCountService;
import pers.platform.core.auth.service.SendMessageToQueueService;
import pers.platform.core.constant.CoreConstant;

/**
 * Created by cc on  2018/1/20
 */

@Slf4j
@Service
public class RecordApiReuestCountServiceImpl implements RecordApiReuestCountService {

    //kafka
    @Autowired
    private SendMessageToQueueService sendMessageToQueueService;

    @Override
    public void sendIncrementApiCountMessage(String ApiUserAuthId) {
        sendMessageToQueueService.send(CoreConstant.QUEUE_CORE_TOPIC,CoreConstant.QUEUE_CORE_KEY_INCREMENT_API_COUNT,ApiUserAuthId);

    }
}
