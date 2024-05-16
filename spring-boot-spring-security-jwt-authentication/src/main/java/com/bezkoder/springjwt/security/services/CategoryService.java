package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Category;

public interface CategoryService {

	Iterable<Category> listAllCategory();

	Category getCategoryById(Integer id);

	Category saveCategory(Category category);

	void deleteCategory(Integer id);

}