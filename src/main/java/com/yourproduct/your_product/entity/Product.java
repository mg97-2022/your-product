package com.yourproduct.your_product.entity;

import com.yourproduct.your_product.auditing.BaseAuditing;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "products")
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private List<String> images;

    private Float price;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id",  nullable = false)
    private Category category;
}
