package com.otus.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order", schema = "order_service", catalog = "postgres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "total_amount", nullable = false, precision = 2)
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "total_discount", nullable = false, precision = 2)
    private BigDecimal discount = BigDecimal.ZERO;

    @Column(name = "total_quantity", nullable = false)
    private Long quantity = 0L;

    @Column(name = "status", nullable = false)
    private String status;

    public Order(String userName, BigDecimal amount, BigDecimal discount, Long quantity) {
        this.userName = userName;
        this.amount = amount;
        this.discount = discount;
        this.quantity = quantity;
        this.status = "created";
    }
}



