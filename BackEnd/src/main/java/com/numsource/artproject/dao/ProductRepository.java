package com.numsource.artproject.dao;

import com.numsource.artproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
    public Product findByProductId(String productId);
    public Product findByUserId(long userId);

}
