package com.otus.orderservice.rabbitmq.domain.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SouIdDTO {

    private List<Long> souIds;

}
