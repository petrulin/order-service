package com.otus.orderservice.service.impl;

import com.otus.orderservice.entity.Order;
import com.otus.orderservice.repository.OrderRepository;
import com.otus.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("orderService")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

}
