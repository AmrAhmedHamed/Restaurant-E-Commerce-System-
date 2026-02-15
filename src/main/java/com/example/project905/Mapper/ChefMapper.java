package com.example.project905.Mapper;

import com.example.project905.Dto.ChefDto;
import com.example.project905.Modle.Chef;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChefMapper {

    ChefDto toDto(Chef chef);

    Chef toEntity(ChefDto chefDto);

    List<ChefDto> toDtoList(List<Chef> chefs);
    List<Chef> toEntityList(List<ChefDto> chefDtos);

}
