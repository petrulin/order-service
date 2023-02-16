package com.otus.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.queues.order-queue}")
    private String orderQueue;
    @Value("${spring.rabbitmq.exchanges.order-exchange}")
    private String orderExchange;


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
    public DirectExchange orderExchange() {
        return new DirectExchange(orderExchange, true, false);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(orderQueue, true, false, false);
    }

    @Bean
    public Binding schoolsSyncBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(orderQueue);
    }


}
