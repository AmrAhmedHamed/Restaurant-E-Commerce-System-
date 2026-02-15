package com.example.project905.Mapper;

import com.example.project905.Dto.CategoryDto;
import com.example.project905.Modle.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto categoryDto(Category category);
    Category category(CategoryDto categoryDto);
   List< CategoryDto> categoryDtoList(List<Category>categorys);
   List< Category> categoryList(List<CategoryDto> categoryDtos);



}
