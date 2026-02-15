package com.example.project905.Dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChefDto {
    private Long id;
    private String name;
    private String spec;
    private String logoPath;
    private String faceLink;
    private String tweLink;
    private String instaLink;
}
