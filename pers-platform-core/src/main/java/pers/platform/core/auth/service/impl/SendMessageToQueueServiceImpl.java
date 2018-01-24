package pers.platform.core.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import pers.platform.core.auth.service.SendMessageToQueueService;

/**
 *
 * Created by cc on  2018/1/24
 */
@Service
public class SendMessageToQueueServiceImpl implements SendMessageToQueueService {

    //kafka
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public boolean send(String topic, String key, Object data) {
        ListenableFuture<SendResult<String, String>> sendResultListenableFuture = kafkaTemplate.send(topic,key,data.toString());
        if (sendResultListenableFuture.isDone()){
            return true;
        }
        //TODO消息发送失败处理，持久化到日志
        return false;
    }
}
