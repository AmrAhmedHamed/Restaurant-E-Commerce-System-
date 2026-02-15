package com.example.project905.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserOrdersHistoryDto {
    private Long userId;
    private String username;
    private List<OrderDto> orders;
    private int totalOrders;
    private double totalSpent;
}