package com.example.project905.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
    private Long id;
    @NotBlank(message = "product.name.not_empty")
        private String name;
    @NotBlank(message = "product.image_path.not_empty")
    private String imagePath;

    private String descrip;

    @NotNull(message = "product.price.not_null")
    @Positive(message = "product.price.positive")
    private Double price;
    @NotNull(message = "product.category_id.not_null")
    private Long categoryId;

}
