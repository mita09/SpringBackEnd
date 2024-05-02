package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Product;

public interface ProductService {

	Iterable<Product> listAllProducts();

	Product getProductById(Integer id);

	Product saveProduct(Product product);

	void deleteProduct(Integer id);

}