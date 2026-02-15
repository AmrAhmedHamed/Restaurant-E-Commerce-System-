package com.example.project905.Service.Impl;

import com.example.project905.Dto.ChefDto;
import com.example.project905.Modle.Category;
import com.example.project905.Modle.Chef;
import com.example.project905.Repo.ChefRepo;
import com.example.project905.Service.ChefService;
import com.example.project905.Mapper.ChefMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChefServiceImpl implements ChefService {

    private  ChefRepo chefRepo;
    private  ChefMapper chefMapper;

    @Autowired
    public ChefServiceImpl(ChefRepo chefRepo, ChefMapper chefMapper) {
        this.chefRepo = chefRepo;
        this.chefMapper = chefMapper;
    }

    @Override
    public List<ChefDto> getAllChefs() {
        List<Chef> chefs = chefRepo.findAll();
        return chefs.stream()
                .map(chefMapper::toDto)
                .toList();
    }

    @Override
    public List<ChefDto> saveList(List<ChefDto> chefDtos) {
        boolean handles = chefDtos.stream().anyMatch(dto -> Objects.nonNull(dto.getId()));
        if (handles) {
            throw new RuntimeException("id.must-be.null");
        }



        List<Chef> chefs = chefMapper.toEntityList(chefDtos);
        chefs = chefRepo.saveAll(chefs);

        return chefMapper.toDtoList(chefs);

    }
}
