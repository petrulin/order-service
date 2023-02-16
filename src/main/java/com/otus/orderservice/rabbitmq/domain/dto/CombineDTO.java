package com.otus.orderservice.rabbitmq.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CombineDTO {

    private Long souId;

    private String name;

    private Long phone;

    private String email;
}
