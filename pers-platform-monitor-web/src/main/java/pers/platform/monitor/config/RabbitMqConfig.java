package pers.platform.monitor.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

/**
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年8月20日
 * @Version:1.0.0
 */
// @Configuration
public class RabbitMqConfig {

    /**
     * 消息交换机名字
     */
    public static final String EXCHANGE = "my-mq-exchange";

    /**
     * 队列key1
     */
    public static final String ROUTINGKEY1 = "queue_one_key";

    // /**
    // * 配置链接信息
    // *
    // * @return
    // */
    // @Bean
    // public ConnectionFactory connectionFactory() {
    // CachingConnectionFactory connectionFactory = new
    // CachingConnectionFactory(
    // "127.0.0.1", 5672);
    // connectionFactory.setUsername("admin");
    // connectionFactory.setPassword("123456");
    // connectionFactory.setVirtualHost("/");
    // connectionFactory.setPublisherConfirms(true); // 必须要设置
    // return connectionFactory;
    // }
    //
    // /**
    // * 配置消息交换机 针对消费者配置 FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
    // * HeadersExchange ：通过添加属性key-value匹配 DirectExchange:按照routingkey分发到指定队列
    // Top
    // */
    // @Bean
    // public DirectExchange directExchange() {
    // return new DirectExchange(EXCHANGE, true, false);
    // }

    /**
     * 配置消息队列1 针对消费者配置
     * 
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue("hello", true); // 队列持久
    }

    // @Bean
    // public Binding bingding() {
    // return BindingBuilder.bind(queue()).to(directExchange())
    // .with(ROUTINGKEY1);
    // }
    //
    // /**
    // * 接受消息的监听，这个监听会接受消息队列1的消息 针对消费者配置
    // *
    // * @return
    // */
    // @Bean
    // public SimpleMessageListenerContainer messageListenerContainer() {
    // SimpleMessageListenerContainer container = new
    // SimpleMessageListenerContainer(
    // connectionFactory());
    // container.setQueues(queue());
    // container.setExposeListenerChannel(true);
    // container.setMaxConcurrentConsumers(1);
    // container.setMaxConcurrentConsumers(1); // 设置确认模式手工确认
    // container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    // container.setMessageListener(new ChannelAwareMessageListener() {
    // @Override
    // public void onMessage(Message message, Channel channel)
    // throws Exception {
    // byte[] body = message.getBody();
    // System.out.println("queue 收到消息" + new String(body));
    // channel.basicAck(
    // message.getMessageProperties().getDeliveryTag(), true); // 确认消息成功消费
    // }
    // });
    // return container;
    // }

}
