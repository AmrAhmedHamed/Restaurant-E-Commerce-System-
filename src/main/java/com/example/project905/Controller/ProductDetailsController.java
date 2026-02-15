package com.example.project905.Controller;

import com.example.project905.Dto.ProductDetailsDto;
import com.example.project905.Service.ProductDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ProductDetailsController")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
@Tag(name = "Product Details Management", description = "APIs for managing product details")
public class ProductDetailsController {

    private final ProductDetailsService productDetailsService;

    @Operation(summary = "Add product details", description = "Add details to a product")
    @PostMapping("/save")
    public ResponseEntity<ProductDetailsDto> save(@Valid @RequestBody ProductDetailsDto productDetailsDto) {
        return ResponseEntity.ok(productDetailsService.save(productDetailsDto));
    }

    @Operation(summary = "Update product details", description = "Update existing product details")
    @PutMapping("/update")
    public ResponseEntity<ProductDetailsDto> update(@Valid @RequestBody ProductDetailsDto productDetailsDto) {
        return ResponseEntity.ok(productDetailsService.update(productDetailsDto));
    }

    @Operation(summary = "Get product details by product ID", description = "Get details of a specific product")
    @GetMapping("/getByProduct/{productId}")
    public ResponseEntity<ProductDetailsDto> getByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(productDetailsService.getByProductId(productId));
    }

    @Operation(summary = "Get all product details", description = "Get details of all products")
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDetailsDto>> getAll() {
        return ResponseEntity.ok(productDetailsService.getAll());
    }

    @Operation(summary = "Delete product details", description = "Delete product details by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete product details by product ID", description = "Delete details of a specific product")
    @DeleteMapping("/deleteByProduct/{productId}")
    public ResponseEntity<Void> deleteByProductId(@PathVariable Long productId) {
        productDetailsService.deleteByProductId(productId);
        return ResponseEntity.noContent().build();
    }
}