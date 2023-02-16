package com.otus.orderservice.repository;

import com.otus.orderservice.entity.Message;
import com.otus.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
