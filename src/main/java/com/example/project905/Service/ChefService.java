package com.example.project905.Service;

import com.example.project905.Dto.ChefDto;

import java.util.List;

public interface ChefService {
    List<ChefDto> getAllChefs();
    List<ChefDto>saveList(List<ChefDto>chefDtos);
}
