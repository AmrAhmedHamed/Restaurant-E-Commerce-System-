package com.example.project905.Repo;

import com.example.project905.Modle.ProductDetails; // تأكد من أنه Modle
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDetailsRepo extends JpaRepository<ProductDetails, Long> {

    @Query("SELECT pd FROM ProductDetails pd WHERE pd.product.id = :productId")
    Optional<ProductDetails> findByProductId(@Param("productId") Long productId);

    // Optional<ProductDetails> findByProduct_Id(Long productId);
}