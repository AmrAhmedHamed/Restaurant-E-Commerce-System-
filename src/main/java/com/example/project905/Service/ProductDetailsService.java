package com.example.project905.Service;

import com.example.project905.Dto.ProductDetailsDto;

import java.util.List;

public interface ProductDetailsService {
    ProductDetailsDto save(ProductDetailsDto productDetailsDto);
    ProductDetailsDto update(ProductDetailsDto productDetailsDto);
    ProductDetailsDto getByProductId(Long productId);
    List<ProductDetailsDto> getAll();
    void delete(Long id);
    void deleteByProductId(Long productId);
}