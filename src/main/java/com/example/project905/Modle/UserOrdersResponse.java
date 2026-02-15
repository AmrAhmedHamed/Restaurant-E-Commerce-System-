package com.example.project905.Modle;


import com.example.project905.Dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserOrdersResponse {
    private List<OrderDto> orders;
    private Integer totalOrders;
    private Double totalPrice;
}