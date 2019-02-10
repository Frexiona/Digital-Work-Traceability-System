package com.numsource.artproject.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Product {
    @Id
    private String productId;
    private String imagePath;
    private long userId;
    private long hot;
    private String info;
    private String author;
}
