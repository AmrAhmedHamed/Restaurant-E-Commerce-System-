package com.example.project905.Mapper;

import com.example.project905.Dto.ProductDetailsDto;
import com.example.project905.Modle.ProductDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductDetailsMapper {

    @Mapping(source = "product.id", target = "productId")
    ProductDetailsDto toDto(ProductDetails productDetails);

    @Mapping(target = "product", ignore = true)
    ProductDetails toEntity(ProductDetailsDto productDetailsDto);

    default ProductDetailsDto productDetailsDto(ProductDetails productDetails) {
        return toDto(productDetails);
    }

    default ProductDetails productDetails(ProductDetailsDto productDetailsDto) {
        return toEntity(productDetailsDto);
    }
}