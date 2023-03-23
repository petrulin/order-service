package com.otus.orderservice.rabbitmq.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMailDTO {

    private String username;
    private String message;
    private String email;
}
