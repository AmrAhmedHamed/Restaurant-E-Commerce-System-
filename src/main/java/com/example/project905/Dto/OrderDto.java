package com.example.project905.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String code;
    private Double totalPrice;
    private Integer totalNumber;
    private List<ProductDto> products;
    private Long userId;
}