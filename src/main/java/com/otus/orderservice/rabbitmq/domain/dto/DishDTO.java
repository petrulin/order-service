
package com.otus.orderservice.rabbitmq.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DishDTO {
    private Long menuId;
    private Long count;

}
