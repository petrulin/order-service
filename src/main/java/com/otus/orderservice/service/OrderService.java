package com.otus.orderservice.service;

import com.otus.orderservice.entity.Order;
import com.otus.orderservice.rabbitmq.domain.dto.OrderDTO;
import com.otus.orderservice.rabbitmq.domain.dto.TrxDTO;


public interface OrderService {

    OrderDTO save(OrderDTO user);

    void result(TrxDTO trxDTO);
}
