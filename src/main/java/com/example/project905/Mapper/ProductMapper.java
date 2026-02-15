package com.example.project905.Mapper;

import com.example.project905.Dto.ProductDto;
import com.example.project905.Modle.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "categoryId", target = "category.id")

    Product product(ProductDto productDto);
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto productDto(Product product);
    List<Product> productList(List<ProductDto> productDto);
    List<ProductDto> productDtoList(List<Product> product);

}
