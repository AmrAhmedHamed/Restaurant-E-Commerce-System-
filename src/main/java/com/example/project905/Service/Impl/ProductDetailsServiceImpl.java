package com.example.project905.Service.Impl;

import com.example.project905.Dto.ProductDetailsDto;
import com.example.project905.Mapper.ProductDetailsMapper;
import com.example.project905.Modle.Product;
import com.example.project905.Modle.ProductDetails;
import com.example.project905.Repo.ProductDetailsRepo;
import com.example.project905.Repo.ProductRepo;
import com.example.project905.Service.ProductDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final ProductDetailsRepo productDetailsRepo;
    private final ProductRepo productRepo;
    private final ProductDetailsMapper productDetailsMapper;

    @Override
    @Transactional
    public ProductDetailsDto save(ProductDetailsDto productDetailsDto) {
        Product product = productRepo.findById(productDetailsDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productDetailsDto.getProductId()));

        if (product.getProductDetails() != null) {
            throw new RuntimeException("Product already has details. Product ID: " + product.getId());
        }

        ProductDetails productDetails = productDetailsMapper.toEntity(productDetailsDto);
        productDetails.setProduct(product);

        ProductDetails savedDetails = productDetailsRepo.save(productDetails);

        product.setProductDetails(savedDetails);
        productRepo.save(product);

        return productDetailsMapper.toDto(savedDetails);
    }
    @Override
    @Transactional
    public ProductDetailsDto update(ProductDetailsDto productDetailsDto) {
        ProductDetails existingDetails = productDetailsRepo.findById(productDetailsDto.getId())
                .orElseThrow(() -> new RuntimeException("Product details not found with id: " + productDetailsDto.getId()));

        if (productDetailsDto.getWeight() != null) {
            existingDetails.setWeight(productDetailsDto.getWeight());
        }
        if (productDetailsDto.getDimensions() != null) {
            existingDetails.setDimensions(productDetailsDto.getDimensions());
        }
        if (productDetailsDto.getColor() != null) {
            existingDetails.setColor(productDetailsDto.getColor());
        }
        if (productDetailsDto.getMaterial() != null) {
            existingDetails.setMaterial(productDetailsDto.getMaterial());
        }
        if (productDetailsDto.getManufacturer() != null) {
            existingDetails.setManufacturer(productDetailsDto.getManufacturer());
        }
        if (productDetailsDto.getOriginCountry() != null) {
            existingDetails.setOriginCountry(productDetailsDto.getOriginCountry());
        }
        if (productDetailsDto.getWarrantyPeriod() != null) {
            existingDetails.setWarrantyPeriod(productDetailsDto.getWarrantyPeriod());
        }
        if (productDetailsDto.getAdditionalInfo() != null) {
            existingDetails.setAdditionalInfo(productDetailsDto.getAdditionalInfo());
        }

        ProductDetails updatedDetails = productDetailsRepo.save(existingDetails);
        return productDetailsMapper.productDetailsDto(updatedDetails);
    }

    @Override
    public ProductDetailsDto getByProductId(Long productId) {
        return productDetailsRepo.findByProductId(productId)
                .map(productDetailsMapper::productDetailsDto)
                .orElseThrow(() -> new RuntimeException("No details found for product id: " + productId));
    }

    @Override
    public List<ProductDetailsDto> getAll() {
        return productDetailsRepo.findAll().stream()
                .map(productDetailsMapper::productDetailsDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ProductDetails details = productDetailsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product details not found with id: " + id));

        if (details.getProduct() != null) {
            details.getProduct().setProductDetails(null);
            productRepo.save(details.getProduct());
        }

        productDetailsRepo.delete(details);
    }

    @Override
    @Transactional
    public void deleteByProductId(Long productId) {
        productDetailsRepo.findByProductId(productId)
                .ifPresent(details -> {
                    if (details.getProduct() != null) {
                        details.getProduct().setProductDetails(null);
                        productRepo.save(details.getProduct());
                    }
                    productDetailsRepo.delete(details);
                });
    }
}