package com.otus.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.queues.order-queue}")
    private String queue;
    @Value("${spring.rabbitmq.exchanges.order-exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.queues.service-answer-queue}")
    private String serviceAnswerQueue;
    @Value("${spring.rabbitmq.exchanges.service-answer-exchange}")
    private String serviceAnswerExchange;
    @Value("${spring.rabbitmq.queues.notification-queue}")
    private String notificationQueue;
    @Value("${spring.rabbitmq.exchanges.notification-exchange}")
    private String notificationExchange;


    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queue() {
        return new Queue(queue, true, false, false);
    }

    @Bean
    public Binding queueSyncBinding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(queue);
    }

    @Bean
    public DirectExchange orderAnswerExchange() {
        return new DirectExchange(serviceAnswerExchange, true, false);
    }

    @Bean
    public Queue orderAnswerQueue() {
        return new Queue(serviceAnswerQueue, true, false, false);
    }

    @Bean
    public Binding orderAnswerQueueSyncBinding() {
        return BindingBuilder.bind(orderAnswerQueue()).to(orderAnswerExchange()).with(serviceAnswerQueue);
    }

    @Bean
    public DirectExchange notificationExchange() {
        return new DirectExchange(notificationExchange, true, false);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(notificationQueue, true, false, false);
    }

    @Bean
    public Binding notificationQueueBinding() {
        return BindingBuilder.bind(notificationQueue()).to(notificationExchange()).with(notificationQueue);
    }

}
