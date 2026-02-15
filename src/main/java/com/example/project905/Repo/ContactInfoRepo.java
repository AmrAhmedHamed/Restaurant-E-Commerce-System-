package com.example.project905.Repo;


import com.example.project905.Modle.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoRepo extends JpaRepository<ContactInfo, Long> {
}
