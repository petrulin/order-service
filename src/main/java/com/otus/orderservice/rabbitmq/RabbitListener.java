package com.otus.orderservice.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otus.orderservice.entity.Message;
import com.otus.orderservice.entity.Order;
import com.otus.orderservice.rabbitmq.domain.RMessage;
import com.otus.orderservice.service.MessageService;
import com.otus.orderservice.service.OrderService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Component
public class RabbitListener {

    private final MessageService messageService;
    private final OrderService orderService;


    public RabbitListener(MessageService messageService,
                          OrderService orderService) {
        this.messageService = messageService;
        this.orderService = orderService;
    }

    @Transactional
    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "${spring.rabbitmq.queues.order-queue}", ackMode = "MANUAL")
    public void orderQueueListener(RMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            var om = new ObjectMapper();
            var msg = messageService.findById(message.getMsgId());
            if (msg == null) {
                messageService.save(new Message(message.getMsgId()));
                switch (message.getCmd()) {
                    case "newOrder" ->  {
                        om.getTypeFactory().constructCollectionType(ArrayList.class, Order.class);
                        var orderMsg = om.convertValue(message.getMessage(), Order.class);
                        orderService.save(orderMsg);
                    }
                    default -> log.warn("::OrderService:: rabbitmq listener method. Unknown message type");
                }
            }
            else {
                log.warn("::OrderService:: rabbitmq listener method orderQueueListener duplicate message!");
            }
        } catch (Exception ex) {
            log.error("::OrderService:: rabbitmq listener method orderQueueListener with error message {}", ex.getLocalizedMessage());
            log.error("::OrderService:: rabbitmq listener method orderQueueListener with stackTrace {}", ExceptionUtils.getStackTrace(ex));
        } finally {
            basicAck(channel, tag, true);
        }
    }

    private void basicAck(Channel channel, Long tag, boolean b) {
        try {
            channel.basicAck(tag, b);
        } catch (IOException ex) {
            log.error("::OrderService:: rabbitmq listener method basicAck with stackTrace {}", ExceptionUtils.getStackTrace(ex));
        }
    }
}
