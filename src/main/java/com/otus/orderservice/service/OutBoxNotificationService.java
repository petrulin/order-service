package com.otus.orderservice.service;

import com.otus.orderservice.entity.OutBoxNotification;
import com.otus.orderservice.rabbitmq.domain.dto.OrderDTO;
import com.otus.orderservice.rabbitmq.domain.dto.TrxDTO;

import java.util.List;


public interface OutBoxNotificationService {

    OutBoxNotification save(OutBoxNotification outBoxNotification);
    List<OutBoxNotification> findAll();

    void deleteAll(List<OutBoxNotification> outBoxNotification);
}
