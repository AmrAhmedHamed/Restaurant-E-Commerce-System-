package com.example.project905.Repo;

import com.example.project905.Modle.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    List<Category> findAllByOrderByNameAsc();

    List<Category> findAllByOrderByIdAsc();
}
