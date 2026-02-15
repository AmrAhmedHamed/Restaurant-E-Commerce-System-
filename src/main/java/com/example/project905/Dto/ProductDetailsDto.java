package com.example.project905.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDto {
    private Long id;
    private Double weight;
    private String dimensions;
    private String color;
    private String material;
    private String manufacturer;
    private String originCountry;
    private Integer warrantyPeriod;
    private String additionalInfo;
    private Long productId;
}