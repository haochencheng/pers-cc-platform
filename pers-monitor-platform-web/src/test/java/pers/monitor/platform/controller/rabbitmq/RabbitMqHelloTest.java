package pers.monitor.platform.controller.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pers.platform.monitor.controller.rabbitmq.HelloSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void send() throws Exception {
        helloSender.send();
    }

    @Test
    public void oneToMany() throws Exception {
        for (int i = 0; i < 20; i++) {
            helloSender.send(i);
        }
    }

}
