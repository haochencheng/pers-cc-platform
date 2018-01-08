package pers.platform.monitor.controller.rabbitmq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import pers.platform.common.config.RabbitMqConfig;

//@Component
public class HelloSender {

    /**
     * springboo默认实现
     */
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello:" + new Date();
        System.out.println("Sender:===========================" + context);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,
                RabbitMqConfig.ROUTINGKEY1, context);
    }

    public void send(int i) {
        String context = "hello:" + i;
        System.out.println("Sender:===========================" + i + context);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,
                RabbitMqConfig.ROUTINGKEY1, context);
    }

}
