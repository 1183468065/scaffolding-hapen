package com.example.controller;

import com.example.config.RabbitMqConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
public class RabbitMQController {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQController.class);
    @Autowired
    private AmqpTemplate amqpTemplate;

    public static final int wait_time = 1000 * 3;

    @GetMapping("/demo/queue")
    public String demo_queue() {
        logger.info("demo_queue start");
        this.amqpTemplate.convertAndSend(RabbitMqConfiguration.demo_queue,"demo_queue param");
        logger.info("demo_queue end");
        return "demo queue send";
    }

    @GetMapping("/demo/exchange/queue")
    public String demo_exchange_queue(Integer waitTime) {
        logger.info("demo_exchange_queue start");
        this.amqpTemplate.convertAndSend(RabbitMqConfiguration.demo_exchange, RabbitMqConfiguration.routingKey, "delay-meaagse-"+waitTime, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader(RabbitMqConfiguration.delayed_message_head,waitTime);
                return message;
            }
        });
        logger.info("demo_exchange_queue end");
        return "demo exchange queue send";
    }

}
