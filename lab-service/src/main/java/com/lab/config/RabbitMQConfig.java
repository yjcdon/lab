package com.lab.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RabbitMQConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate (ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        /*
         * Mandatory为true时,消息通过交换器无法匹配到队列会返回给生产者，为false时匹配不到会直接被丢弃
         * 在这里设置了，就不需要在配置文件中设置了
         * */
        rabbitTemplate.setMandatory(true);
        // 设置消息转换器
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(new ObjectMapper()));

        /*
         * 消息无法到达 exchange 时会走这里
         * 比如找不到交换机
         * */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                log.error("消息发送失败，correlationData：{}，原因：{}", correlationData, cause);
                // 做其他事
            }
        });

        /*
         * 消息无法路由到队列时会走这里，此时消息不可达；
         * 比如 routingKey 错误
         * */
        rabbitTemplate.setReturnsCallback(returned -> {
            log.warn("被退回的消息为：{}", returned.getMessage());
            log.warn("replyCode：{}", returned.getReplyCode());
            log.warn("replyText：{}", returned.getReplyText());
            log.warn("exchange：{}", returned.getExchange());
            log.warn("routingKey：{}", returned.getRoutingKey());
            // 做其他事
        });

        return rabbitTemplate;
    }

    @Bean
    public MessageRecoverer messageRecoverer (RabbitTemplate rabbitTemplate) {
        return new RepublishMessageRecoverer(rabbitTemplate, "error", "error");
    }
}