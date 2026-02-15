package com.example.project905.Service.Impl;

import com.example.project905.Dto.CategoryDto;
import com.example.project905.Mapper.CategoryMapper;
import com.example.project905.Modle.Category;
import com.example.project905.Repo.CategoryRepo;
import com.example.project905.Service.CategorySerice;
import com.example.project905.Service.MassegeHandler.bandlMassegeService;
import com.example.project905.Service.PaginationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategroServiceImpl implements CategorySerice {
    private CategoryRepo categoryRepo;
    private CategoryMapper categoryMapper;
    private  bandlMassegeService messageService;
    private  PaginationService paginationService;

    public CategroServiceImpl(CategoryRepo categoryRepo, CategoryMapper categoryMapper,
                              bandlMassegeService messageService, PaginationService paginationService) {
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
        this.messageService = messageService;
        this.paginationService = paginationService;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream()
                .map(categoryMapper::categoryDto)
                .collect(Collectors.toList());
    }
    @Override
    public CategoryDto save(CategoryDto CategoryDto) {
        if (Objects.nonNull(CategoryDto.getId())) {
            throw new RuntimeException("id.must-be.null");
        }
        Category category = categoryMapper.category(CategoryDto);
        category = categoryRepo.save(category);
        return categoryMapper.categoryDto(category);
    }

    @Override
    public List<CategoryDto> saveList(List<CategoryDto> CategoryDtos) {
        boolean handles = CategoryDtos.stream().anyMatch(dto -> Objects.nonNull(dto.getId()));
        if (handles) {
            throw new RuntimeException("id.must-be.null");
        }


        List<Category> categories = categoryMapper.categoryList(CategoryDtos);
        categories = categoryRepo.saveAll(categories);

        return categoryMapper.categoryDtoList(categories);
    }

    @Override
    public CategoryDto update(CategoryDto CategoryDto) {
        if (Objects.isNull(CategoryDto.getId())) {
            throw new RuntimeException("id.must_be.not_null");
        }
        Category category = categoryMapper.category(CategoryDto);
        category = categoryRepo.save(category);
        return categoryMapper.categoryDto(category);
    }

    @Override
    public List<CategoryDto> updateList(List<CategoryDto> CategoryDto) {
        boolean handle=CategoryDto.stream().anyMatch
                (CategoryDtos -> Objects.isNull(CategoryDtos.getId()));
        if (handle){
            throw new RuntimeException("id.must_be.not_null");
        }
        List<Category> categories = categoryMapper.categoryList(CategoryDto);
        categories = categoryRepo.saveAll(categories);

        return categoryMapper.categoryDtoList(categories);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isEmpty())
            return false;
        categoryRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteList(List<Long> id) {
        List<Category> categories = categoryRepo.findAllById(id) ;
            if (categories.isEmpty()) {
                return false;

            }categoryRepo.deleteAllById(id);
            return true;
        }

    @Override
    public List<CategoryDto> getAllByNameAsc() {
        List<Category> categories = categoryRepo.findAllByOrderByNameAsc();
        return categories.stream()
                .map(categoryMapper::categoryDto)
                .toList();
    }

    @Override
    public List<CategoryDto> getAllByIdAsc() {
        List<Category> categories = categoryRepo.findAllByOrderByIdAsc();
        return categories.stream()
                .map(categoryMapper::categoryDto)
                .toList();
    }
    }

