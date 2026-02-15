package com.example.project905.Service;

import com.example.project905.Dto.CategoryDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategorySerice {
    List<CategoryDto> getAllCategories();
    CategoryDto save(CategoryDto CategoryDto);
    List<CategoryDto> saveList(List<CategoryDto> CategoryDtos);
    CategoryDto update(CategoryDto CategoryDto);
    List<CategoryDto> updateList(List<CategoryDto> CategoryDto);
    boolean delete(Long id);
    boolean deleteList(List<Long>id);
    List<CategoryDto> getAllByNameAsc();
    List<CategoryDto> getAllByIdAsc();
}
