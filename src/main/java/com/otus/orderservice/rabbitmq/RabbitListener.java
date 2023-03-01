package com.otus.orderservice.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otus.orderservice.entity.Message;
import com.otus.orderservice.entity.Order;
import com.otus.orderservice.rabbitmq.domain.RMessage;
import com.otus.orderservice.rabbitmq.domain.dto.OrderDTO;
import com.otus.orderservice.rabbitmq.domain.dto.TrxDTO;
import com.otus.orderservice.service.MessageService;
import com.otus.orderservice.service.OrderService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitListener {

    private final MessageService messageService;
    private final OrderService orderService;
    private final RabbitTemplate rt;

    @Value("${spring.rabbitmq.queues.service-answer-queue}")
    private String serviceAnswerQueue;
    @Value("${spring.rabbitmq.exchanges.service-answer-exchange}")
    private String serviceAnswerExchange;

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
                        om.getTypeFactory().constructCollectionType(ArrayList.class, OrderDTO.class);
                        var orderMsg = om.convertValue(message.getMessage(), OrderDTO.class);
                        rt.convertAndSend(serviceAnswerExchange, serviceAnswerQueue,
                                new RMessage(UUID.randomUUID().toString(), "sale", new TrxDTO(orderService.save(orderMsg)))
                        );
                    }
                    case "result" ->  {
                        om.getTypeFactory().constructCollectionType(ArrayList.class, TrxDTO.class);
                        var trxDTO = om.convertValue(message.getMessage(), TrxDTO.class);
                        orderService.result(trxDTO);
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
