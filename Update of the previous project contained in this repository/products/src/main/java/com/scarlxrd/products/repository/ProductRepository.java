package com.scarlxrd.products.repository;

import com.scarlxrd.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
