package com.otus.orderservice.rabbitmq.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolDTO {

    private Long souId;

    private Long souCombineId;

    private String name;

    private Long unp;
}
