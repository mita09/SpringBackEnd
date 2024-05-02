package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.springjwt.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
