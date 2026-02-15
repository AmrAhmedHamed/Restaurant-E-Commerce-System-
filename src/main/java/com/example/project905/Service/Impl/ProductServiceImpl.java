package com.example.project905.Service.Impl;

import com.example.project905.Config.CustomException;
import com.example.project905.Dto.ProductDto;
import com.example.project905.Mapper.ProductMapper;
import com.example.project905.Modle.Product;
import com.example.project905.Repo.ProductRepo;
import com.example.project905.Service.MassegeHandler.bandlMassegeService;
import com.example.project905.Service.PaginationService;
import com.example.project905.Service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final bandlMassegeService messageService;
    private final PaginationService paginationService;

    public ProductServiceImpl(ProductRepo productRepo, ProductMapper productMapper,
                              bandlMassegeService messageService, PaginationService paginationService) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
        this.messageService = messageService;
        this.paginationService = paginationService;
    }

    @Override
    @Cacheable(value = "products", key = "'allProducts_page_' + #pageNumber + '_size_' + #pageSize")
    public Page<ProductDto> getAll(int pageNumber, int pageSize) {
        Pageable pageable = paginationService.getPageable(pageNumber, pageSize);

        Page<Product> page = productRepo.findAll(pageable);

        if (page.getContent().isEmpty()) {
            return page.map(productMapper::productDto);
        }
        return page.map(productMapper::productDto);
    }

    @Override
    @Cacheable(value = "products",
            key = "'category_' + #categoryId + '_page_' + #pageNumber + '_size_' + #pageSize")
    public Page<ProductDto> getProductsByCategory(Long categoryId, int pageNumber, int pageSize) {
        Pageable pageable = paginationService.getPageable(pageNumber, pageSize);

        Page<Product> page = productRepo.findByCategoryId(categoryId, pageable);

        if (page.isEmpty()) {
            throw new CustomException(
                    messageService.getMassegeEn("no.products"),
                    messageService.getMassegeAr("no.products")
            );
        }

        return page.map(productMapper::productDto);
    }

    @Override
    @Cacheable(value = "products",
            key = "'search_' + #keyword + '_page_' + #pageNumber + '_size_' + #pageSize")
    public Page<ProductDto> searchProducts(String keyword, int pageNumber, int pageSize) {
        Pageable pageable = paginationService.getPageable(pageNumber, pageSize);

        Page<Product> page = productRepo.findByNameContainingIgnoreCaseOrDescripContainingIgnoreCase(
                keyword, keyword, pageable);

        if (page.getContent().isEmpty()) {
            throw new CustomException(
                    messageService.getMassegeEn("no.products"),
                    messageService.getMassegeAr("no.products")
            );
        }

        return page.map(productMapper::productDto);
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public ProductDto save(ProductDto productDto) {
        if (Objects.nonNull(productDto.getId())) {
            throw new RuntimeException("id.must-be.null");
        }
        Product product = productMapper.product(productDto);
        product = productRepo.save(product);
        return productMapper.productDto(product);
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public List<ProductDto> saveList(List<ProductDto> productDtos) {
        boolean handle = productDtos.stream().anyMatch(
                productDto -> Objects.nonNull(productDto.getId()));
        if (handle) {
            throw new RuntimeException("id.must-be.null");
        }

        List<Product> products = productMapper.productList(productDtos);
        products = productRepo.saveAll(products);
        return productMapper.productDtoList(products);
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public ProductDto update(ProductDto productDto) {
        if (Objects.isNull(productDto.getId())) {
            throw new RuntimeException("id.must_be.not_null");
        }

        Product product = productMapper.product(productDto);
        product = productRepo.save(product);

        return productMapper.productDto(product);
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public List<ProductDto> updateList(List<ProductDto> productDtos) {
        boolean handle = productDtos.stream().anyMatch(
                productDo -> Objects.isNull(productDo.getId()));
        if (handle) {
            throw new RuntimeException("id.must_be.not_null");
        }

        List<Product> products = productMapper.productList(productDtos);
        products = productRepo.saveAll(products);
        return productMapper.productDtoList(products);
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public boolean delete(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isEmpty()) {
            return false;
        }
        productRepo.deleteById(id);
        return true;
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public boolean deleteList(List<Long> ids) {
        List<Product> products = productRepo.findAllById(ids);
        if (products.isEmpty()) {
            return false;
        }
        productRepo.deleteAllById(ids);
        return true;
    }
}