package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.springjwt.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

//	public List<Product> findByc_id(Integer cid);

	public List<Product> findByCategoryCategoryId(Integer categoryId);

}
