    package com.example.project905.Service;


    import com.example.project905.Dto.ProductDto;
    import org.springframework.data.domain.Page;

    import java.util.List;

    public interface ProductService {
        Page<ProductDto> getAll(int pageNumber, int pageSize);
        Page<ProductDto> getProductsByCategory(Long categoryId, int pageNumber, int pageSize);
        ProductDto save(ProductDto productDto);
        List<ProductDto> saveList(List<ProductDto> productDto);
        ProductDto update(ProductDto productDto);
        List<ProductDto> updateList(List<ProductDto> productDto);
        boolean delete(Long id);
        boolean deleteList(List<Long>id);

        Page<ProductDto> searchProducts(String keyword, int pageNumber, int pageSize);




    }
