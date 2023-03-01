package com.otus.orderservice.service.impl;

import com.otus.orderservice.entity.Order;
import com.otus.orderservice.rabbitmq.domain.dto.OrderDTO;
import com.otus.orderservice.rabbitmq.domain.dto.TrxDTO;
import com.otus.orderservice.repository.OrderRepository;
import com.otus.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("orderService")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDTO save(OrderDTO orderDTO) {

        Order order = new Order(orderDTO.getUserName(), orderDTO.getAmount(), orderDTO.getDiscount(), orderDTO.getQuantity());

        var newOrder = orderRepository.save(order);
        orderDTO.setOrderId(newOrder.getId());
        return orderDTO;

    }

    @Override
    public void result(TrxDTO trxDTO) {
        Order order = orderRepository.findById(trxDTO.getOrder().getOrderId()).orElse(null);
        if (order!= null) {
            if ((trxDTO.getDeliveryStatus().equals("Ok")
                 && trxDTO.getPayStatus().equals("Ok")
                 && trxDTO.getStoreStatus().equals("Ok"))) {
                order.setStatus("Completed");
            } else {
                order.setStatus("Canceled");
            }
            orderRepository.save(order);
        }
    }

}
