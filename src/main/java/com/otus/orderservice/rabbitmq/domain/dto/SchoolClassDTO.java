package com.otus.orderservice.rabbitmq.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClassDTO {

    private Long souId;

    private Long souSchoolId;

    private String name;

}
