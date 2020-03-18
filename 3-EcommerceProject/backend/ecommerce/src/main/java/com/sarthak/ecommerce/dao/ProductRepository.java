package com.sarthak.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarthak.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	

}
