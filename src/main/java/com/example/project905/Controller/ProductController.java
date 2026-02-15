    package com.example.project905.Controller;
    
    import com.example.project905.Dto.ProductDto;
    import com.example.project905.Service.ProductService;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.Parameter;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.responses.ApiResponses;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    
    import java.util.List;
    
    @RestController
    @CrossOrigin("http://localhost:4200")
    @RequiredArgsConstructor
    @Tag(name = "Product Management", description = "APIs for managing products")
    public class ProductController {
    
        private ProductService productService;
    
        @Autowired
        public ProductController(ProductService productService) {
            this.productService = productService;
        }
    
        @Operation(summary = "Get all products", description = "Retrieve paginated list of all products")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "Successfully retrieved products"),
                @ApiResponse(responseCode = "400", description = "Invalid parameters")
        })
        @GetMapping("/ProductController/getAll")
        public ResponseEntity<?> getAll(
                @Parameter(description = "Page number (0-based)") @RequestParam int pageNumber,
                @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int pageSize) {
            return ResponseEntity.ok(productService.getAll(pageNumber, pageSize));
        }
    
        @Operation(summary = "Search products", description = "Search products by keyword with pagination")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "Successfully searched products"),
                @ApiResponse(responseCode = "404", description = "No products found")
        })
        @GetMapping("/ProductController/search")
        public ResponseEntity<?> search(
                @Parameter(description = "Search keyword") @RequestParam String keyword,
                @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int pageNumber,
                @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int pageSize) {
            return ResponseEntity.ok(productService.searchProducts(keyword, pageNumber, pageSize));
        }
    
        @Operation(summary = "Get products by category", description = "Get products by category ID with pagination")
        @GetMapping("/ProductController/getByCategory")
        public ResponseEntity<?> getProductsByCategory(
                @Parameter(description = "Category ID") @RequestParam Long categoryId,
                @Parameter(description = "Page number (0-based)") @RequestParam int pageNumber,
                @Parameter(description = "Page size") @RequestParam int pageSize) {
            return ResponseEntity.ok(productService.getProductsByCategory(categoryId, pageNumber, pageSize));
        }
    
        @Operation(summary = "Create product", description = "Create a new product")
        @PostMapping("/ProductController/save")
        public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductDto productDto) {
            return ResponseEntity.ok(productService.save(productDto));
        }
    
        @Operation(summary = "Create multiple products", description = "Create multiple products at once")
        @PostMapping("/ProductController/saveAll")
        public ResponseEntity<List<ProductDto>> saveAll(@Valid @RequestBody List<ProductDto> productDtos) {
            return ResponseEntity.ok(productService.saveList(productDtos));
        }
    
        @Operation(summary = "Update product", description = "Update an existing product")
        @PutMapping("/ProductController/update")
        public ResponseEntity<ProductDto> update(@Valid @RequestBody ProductDto productDto) {
            return ResponseEntity.ok(productService.update(productDto));
        }
    
        @Operation(summary = "Update multiple products", description = "Update multiple products at once")
        @PutMapping("/ProductController/updateAll")
        public ResponseEntity<List<ProductDto>> updateAll(@Valid @RequestBody List<ProductDto> productDtos) {
            return ResponseEntity.ok(productService.updateList(productDtos));
        }
    
        @Operation(summary = "Delete product", description = "Delete a product by ID")
        @DeleteMapping("/ProductController/delete/{id}")
        public ResponseEntity<Void> delete(@Parameter(description = "Product ID") @PathVariable Long id) {
            boolean deleted = productService.delete(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }
    
        @Operation(summary = "Delete multiple products", description = "Delete multiple products by IDs")
        @DeleteMapping("/ProductController/deleteList/{ids}")
        public ResponseEntity<Void> deleteList(@Parameter(description = "List of product IDs") @PathVariable List<Long> ids) {
            boolean deleted = productService.deleteList(ids);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }
    }