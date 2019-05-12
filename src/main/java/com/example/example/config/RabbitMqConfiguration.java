package com.example.example.config;//package com.example.demo.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * rabbitmq有两种方式可以实现延时消息，
// * 1、RabbitMQ可以针对队列设置x-expires(则队列中所有的消息都有相同的过期时间)或者针对Message
// * 设置x-message-ttl(对消息进行单独设置，每条消息TTL可以不同)，来控制消息的生存时间，如果超时(两者同时设置以最先到期的时间为准)，
// * 则消息变为dead letter(死信)，然后RabbitMQ的Queue可以配置x-dead-letter-exchange和x-dead-letter-routing-key（可选）两个参数，
// * 如果队列内出现了dead letter，则按照这两个参数重新路由转发到指定的队列。
// * x-dead-letter-exchange：出现dead letter之后将dead letter重新发送到指定exchange
// * x-dead-letter-routing-key：出现dead letter之后将dead letter重新按照指定的routing-key发送
// * 随后指定的队列收到死信，从而实现延时消息
// * 2、利用Rabbitmq的插件x-delay-message实现
// * 将exchange和queue绑定
// * 设置消息过期时间，send到exchange中，消息过期后会转入绑定的queue中
// * 监听此queue即可
// */
//@Configuration
//public class RabbitMqConfiguration {
//
//    /**
//     * demo 队列
//     */
//    private final String demo = "DEMO_QUEUE";
//
//    /**
//     * demo 队列
//     */
//    private final String demo = "DEMO_QUEUE";
//
//    @Bean
//    public Queue beforePlaceAnOrderQueue() {
//        Queue beforePlaceAnOrderQueue = new Queue(demo);
//        return beforePlaceAnOrderQueue;
//    }
//
//    @Bean
//    public Queue afterPlaceAnOrderQueue() {
//        Queue afterPlaceAnOrderQueue = new Queue(AFTER_PLACE_AN_ORDER_QUEUE);
//        return afterPlaceAnOrderQueue;
//    }
//
//    @Bean
//    public Queue afterPaymentAnOrderQueue() {
//        Queue afterPaymentAnOrderQueue = new Queue(AFTER_PAYMENT_AN_ORDER_QUEUE);
//        return afterPaymentAnOrderQueue;
//    }
//
//    @Bean
//    public DirectExchange afterPaymentAnOrderExchange() {
//        DirectExchange afterPaymentAnOrderExchange = new DirectExchange(AFTER_PLACE_AN_ORDER_EXCHANGE, true, false);
//        afterPaymentAnOrderExchange.setDelayed(true);
//        return afterPaymentAnOrderExchange;
//    }
//
//    @Bean
//    Binding binding(Queue afterPlaceAnOrderQueue, DirectExchange afterPaymentAnOrderExchange) {
//        return BindingBuilder.bind(afterPlaceAnOrderQueue).to(afterPaymentAnOrderExchange).with(AFTER_PAYMENT_AN_ORDER_QUEUE);
//    }
//}
