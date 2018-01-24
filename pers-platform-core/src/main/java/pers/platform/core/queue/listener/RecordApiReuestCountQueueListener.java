package pers.platform.core.queue.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface RecordApiReuestCountQueueListener {

    void listener(ConsumerRecord<?,?> consumerRecord);
}
