package pers.platform.core.queue.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pers.platform.core.auth.repository.ApiAccessCounterRepo;
import pers.platform.core.constant.CoreConstant;
import pers.platform.core.queue.RecordApiReuestCountQueueListener;

@Service
public class RecordApiReuestCountQueueListenerImpl implements RecordApiReuestCountQueueListener {

    private final Logger logger= LoggerFactory.getLogger(RecordApiReuestCountQueueListener.class);

    @Autowired
    private ApiAccessCounterRepo apiAccessCounterRepo;

    @Override
    @KafkaListener(topics = CoreConstant.QUEUE_CORE_TOPIC)
    public void listener(ConsumerRecord<?, ?> consumerRecord) {
        if (CoreConstant.QUEUE_CORE_KEY_INCREMENT_API_COUNT.equals(consumerRecord.key())){
            int incrementFlag=apiAccessCounterRepo.incrementApiCount((String) consumerRecord.value());
            if (incrementFlag==0){
                //更新失败处理
                //TODO
                logger.error("主题：{}中key：{}消费失败，消费value：{}"+CoreConstant.QUEUE_CORE_TOPIC+CoreConstant.QUEUE_CORE_KEY_INCREMENT_API_COUNT+consumerRecord.value());
            }
        }
    }
}
