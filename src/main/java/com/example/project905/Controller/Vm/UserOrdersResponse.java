package com.example.project905.Controller.Vm;


import com.example.project905.Dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserOrdersResponse {

    private List<OrderDto> orderDtos;

    private Integer size;

    private Double price;


}