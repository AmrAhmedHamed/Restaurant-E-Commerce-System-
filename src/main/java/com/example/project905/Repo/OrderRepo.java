package com.example.project905.Repo;

import com.example.project905.Modle.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
//    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.products LEFT JOIN FETCH o.user WHERE o.user.id = :userId")
    List<Order> findByUserId(Long userId);

    @EntityGraph(attributePaths = {"products", "user"})
    Page<Order> findAll(Pageable pageable);



}
