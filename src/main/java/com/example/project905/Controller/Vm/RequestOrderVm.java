package com.example.project905.Controller.Vm;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestOrderVm {
    @NotEmpty(message = "productIds.must.not.be.empty")
    private List<Long> productsIds;

    @NotNull(message = "totalPrice.must.not.be.null")
    @Min(value = 1, message = "totalPrice.must.be.positive")
    private Double totalPrice;

    @NotNull(message = "totalNumber.must.not.be.null")
    @Min(value = 1, message = "totalNumber.must.be.positive")
    private Integer totalNumber;


}