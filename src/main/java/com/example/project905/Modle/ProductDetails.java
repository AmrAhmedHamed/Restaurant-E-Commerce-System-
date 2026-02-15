// تغيير السطر الأول فقط
package com.example.project905.Modle;

import com.example.project905.Modle.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "dimensions")
    private String dimensions;

    @Column(name = "color")
    private String color;

    @Column(name = "material")
    private String material;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "origin_country")
    private String originCountry;

    @Column(name = "warranty_period")
    private Integer warrantyPeriod;

    // تصحيح مشكلة Oracle: استبدل TEXT بـ CLOB
    @Lob
    @Column(name = "additional_info", columnDefinition = "CLOB") // تغيير هنا فقط
    private String additionalInfo;

    @OneToOne
    @JoinColumn(name = "product_id", unique = true)
    @JsonIgnore
    private Product product;
}