package com.otus.orderservice.schedule;

import com.otus.orderservice.rabbitmq.domain.RMessage;
import com.otus.orderservice.rabbitmq.domain.dto.CreateMailDTO;
import com.otus.orderservice.service.OutBoxNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.UUID;

@Slf4j
@Component("syncSchedule")
@RequiredArgsConstructor
public class OutBoxSchedule {

  private final OutBoxNotificationService outBoxNotificationService;
  private final RabbitTemplate rt;

    @Value("${spring.rabbitmq.queues.notification-queue}")
    private String notificationQueue;
    @Value("${spring.rabbitmq.exchanges.notification-exchange}")
    private String notificationExchange;

  @Scheduled(fixedDelay = 10000)
  @Transactional
  public void outBoxSchedule(){
      try {
          var userMessageList = outBoxNotificationService.findAll();
          userMessageList.forEach(n-> rt.convertAndSend(notificationExchange, notificationQueue,
                  new RMessage(UUID.randomUUID().toString(), "email", new CreateMailDTO(n.getUserName(), n.getMessage(), n.getEmail()))));
          outBoxNotificationService.deleteAll(userMessageList);
      } catch (Exception ex){
          log.error("::OrderService:: outBoxSchedule error:" + ex.getLocalizedMessage());
      }

  }

}
