package com.example.project905.Service.Impl;
import com.example.project905.Dto.ContactInfoDto;
import com.example.project905.Mapper.ContactInfoMapper;
import com.example.project905.Modle.ContactInfo;
import com.example.project905.Repo.ContactInfoRepo;
import com.example.project905.Service.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactInfoRepo contactInfoRepo;
    private final ContactInfoMapper contactInfoMapper;

    @Autowired
    public ContactInfoServiceImpl(ContactInfoRepo contactInfoRepo,
                                  ContactInfoMapper contactInfoMapper) {
        this.contactInfoRepo = contactInfoRepo;
        this.contactInfoMapper = contactInfoMapper;
    }

    @Override
    public ContactInfoDto save(ContactInfoDto contactInfoDto) {
        if (Objects.nonNull(contactInfoDto.getId())) {
            throw new RuntimeException("id.must-be.null");
        }
        ContactInfo contactInfo = contactInfoMapper.toEntity(contactInfoDto);
        contactInfo = contactInfoRepo.save(contactInfo);
        return contactInfoMapper.toDto(contactInfo);
    }
}
