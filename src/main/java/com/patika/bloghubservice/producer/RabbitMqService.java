package com.patika.bloghubservice.producer;

import com.patika.bloghubservice.config.RabbitMQConfig;
import com.patika.bloghubservice.dto.producer.SendEmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMqService {


    private final AmqpTemplate rabbitTemplate;


    private final RabbitMQConfig rabbitMQConfig;

    public void sendEmail(SendEmailMessage sendEmailMessage) {
        rabbitTemplate.convertAndSend(rabbitMQConfig.getExchange(), rabbitMQConfig.getRoutingkey(), sendEmailMessage);
        log.info("Mail sent to RabbitMQ. Queue: {}, message: {}", rabbitMQConfig.getQueueName(), sendEmailMessage);
    }

}
