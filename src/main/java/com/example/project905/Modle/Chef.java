package com.example.project905.Modle;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String spec;
    private String logoPath;
    private String faceLink;
    private String tweLink;
    private String instaLink;
}

