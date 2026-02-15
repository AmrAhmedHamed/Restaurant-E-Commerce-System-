package com.example.project905.Controller;



import com.example.project905.Dto.ContactInfoDto;
import com.example.project905.Service.ContactInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactInfoController {

    private final ContactInfoService contactInfoService;

    @Autowired
    public ContactInfoController(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
    }

    @PostMapping("/ContactInfoController/saveContact")
    public ResponseEntity<ContactInfoDto> saveContact(@Valid @RequestBody ContactInfoDto dto) {
        return ResponseEntity.ok(contactInfoService.save(dto));
    }
}
