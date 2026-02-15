package com.example.project905.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Category Dto",
        description = "category dto contains (id,name,logo,flag,products)"
)
public class CategoryDto {
    private Long id ;

    @NotBlank(message = "product.name.not_empty")
    @Schema(
            name = "name",
            description = "name for category (string)",
            example = "taher amin"
    )
    private String name;

    @NotBlank(message = "country.flag.not_empty")
    private String flag;
    private String logo;
}
