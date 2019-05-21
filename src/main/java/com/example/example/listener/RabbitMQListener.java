package com.example.example.listener;

import com.example.example.config.RabbitMqConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    @RabbitListener(queues = {RabbitMqConfiguration.demo_queue})
    public void demo_queueListeren(String param) {
        logger.info("demo_queueListeren start param:{}", param);
        logger.info("demo_queueListeren end");
    }

    @RabbitListener(queues = {RabbitMqConfiguration.demo_exchange_binding_queue})
    public void demo_exchange_binding_queueListeren(String param) {
        logger.info("demo_exchange_binding_queueListeren start param:{}", param);
        logger.info("demo_exchange_binding_queueListeren end");
    }
}
