package com.otus.orderservice.service.impl;

import com.otus.orderservice.entity.Order;
import com.otus.orderservice.entity.OutBoxNotification;
import com.otus.orderservice.rabbitmq.domain.dto.OrderDTO;
import com.otus.orderservice.rabbitmq.domain.dto.TrxDTO;
import com.otus.orderservice.repository.OrderRepository;
import com.otus.orderservice.repository.OutBoxNotificationRepository;
import com.otus.orderservice.service.OrderService;
import com.otus.orderservice.service.OutBoxNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("outBoxNotificationService")
@RequiredArgsConstructor
public class OutBoxNotificationServiceImpl implements OutBoxNotificationService {
    private final OutBoxNotificationRepository outBoxNotificationRepository;

    @Override
    public OutBoxNotification save(OutBoxNotification outBoxNotification) {
        return outBoxNotificationRepository.save(outBoxNotification);
    }

    @Override
    public List<OutBoxNotification> findAll() {
        return outBoxNotificationRepository.findAll();
    }
    @Override
    public void deleteAll(List<OutBoxNotification> outBoxNotification) {
        outBoxNotificationRepository.deleteAll(outBoxNotification);
    }

}
