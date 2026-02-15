package com.example.project905.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDto {

    private Long id;
    @NotEmpty(message = "contact.name.not_empty")
    private String name;

    @Email(message = "contact.email.invalid")
    @NotEmpty(message = "contact.email.not_empty")
    private String email;

    @NotEmpty(message = "contact.subject.not_empty")
    private String subject;

    @NotEmpty(message = "contact.message.not_empty")
    private String message;}
