package pers.monitor.platform.controller.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

//@Component
//@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public void receiveMessage(String hello) {
        System.out.println("Receiver===============================:" + hello);
    }

}
