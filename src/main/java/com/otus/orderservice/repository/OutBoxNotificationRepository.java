package com.otus.orderservice.repository;

import com.otus.orderservice.entity.OutBoxNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutBoxNotificationRepository extends JpaRepository<OutBoxNotification, Long> {
}
