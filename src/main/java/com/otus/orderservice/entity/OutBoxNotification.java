package com.otus.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "outbox_notification", schema = "order_service", catalog = "postgres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutBoxNotification {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "email", nullable = false)
    private String email;

    public OutBoxNotification(String userName, String message, String email) {
        this.userName = userName;
        this.message = message;
        this.email = email;
    }
}



