package com.bezkoder.springjwt.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.repository.CategoryRepository;

/**
 * Product service implement.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Iterable<Category> listAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Integer id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);

	}

	@Override
	public void deleteCategory(Integer id) {
		categoryRepository.deleteById(id);
	}

}