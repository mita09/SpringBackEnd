package com.bezkoder.springjwt.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.repository.ProductRepository;
import com.bezkoder.springjwt.security.services.CategoryService;

import jakarta.validation.Valid;

/**
 * Product controller.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * List all products.
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public Iterable<Category> list(Model model) {
		model.addAttribute("products", categoryService.listAllCategory());
		System.out.println("Returning products:");
		return categoryService.listAllCategory();
	}
	@GetMapping("/cwithproduct/{id}")
	public List<Product> cwithproduct(@PathVariable Integer id) {

//		List<Product> p=productRepository.findByc_id(id);
//		System.out.println(p.size());
		List<Product> p1=productRepository.findByCategoryCategoryId(id);
		System.out.println("---"+p1.size());
		return p1;
	}

	/**
	 * View a specific product by its id.
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/{id}")
	public Category showProduct(@PathVariable Integer id) {
		return categoryService.getCategoryById(id);
	}

	// Afficher le formulaire de modification du Product
	@PutMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, @Valid @RequestBody Category category) {
		Category cat = categoryService.getCategoryById(id);
		cat.setDescription(category.getDescription());
		cat.setName(category.getName());
		categoryService.saveCategory(cat);
		return "productform";
	}

	/**
	 * Save product to database.
	 *
	 * @param product
	 * @return
	 */
	@PostMapping("/save")
	public Category saveProduct(@Valid @RequestBody Category category) {
		category.setCreatedDate(new Date());
		Category category2 = categoryService.saveCategory(category);
		return category2;
	}

	/**
	 * Delete product by its id.
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		categoryService.deleteCategory(id);
		return "redirect:/products";
	}

}