package com.example.project905.Mapper;

import com.example.project905.Dto.ContactInfoDto;
import com.example.project905.Modle.ContactInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {


    ContactInfoDto toDto(ContactInfo contactInfo);

    ContactInfo toEntity(ContactInfoDto dto);
}
