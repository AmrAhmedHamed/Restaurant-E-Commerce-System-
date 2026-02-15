package com.example.project905.Controller.Vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderVm {
    private String code;
    private Double totalPrice;
    private Integer totalNumber;
}