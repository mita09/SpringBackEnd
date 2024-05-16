package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.security.services.CategoryService;
import com.bezkoder.springjwt.security.services.ProductService;

import jakarta.validation.Valid;

/**
 * Product controller.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * List all products.
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public List<Product> list(Model model) {
		model.addAttribute("products", productService.listAllProducts());
		model.addAttribute("categories", categoryService.listAllCategory());

		System.out.println("Returning products:");
		return (List<Product>) productService.listAllProducts();
	}

	/**
	 * View a specific product by its id.
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/{id}")
	public Product showProduct(@PathVariable Integer id) {
		return productService.getProductById(id);
	}

	// Afficher le formulaire de modification du Product
	@PutMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, @Valid @RequestBody Product product) {
		Product product2 = productService.getProductById(id);
		product2.setAvailable(product.isAvailable());
		product2.setName(product.getName());
		product2.setPrice(product.getPrice());
		productService.saveProduct(product2);
		return "productform";
	}

	/**
	 * Save product to database.
	 *
	 * @param product
	 * @return
	 */
	@PostMapping("/save")
	public String saveProduct(@Valid @RequestBody Product product) {
		productService.saveProduct(product);
		return "redirect:/product/" + product.getProductId();
	}

	// http://localhost:8088/products/saveproductwithimage
	// Payload - body- formdata : image,name,price,isAvailable
	@PostMapping(value = "/saveproductwithimage", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveProduct(@RequestParam("image") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("price") BigDecimal price, boolean isAvailable,
			@RequestParam("category_id") Integer category_id) {
		Product p = new Product();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (fileName.contains("..")) {
			System.out.println("not a a valid file");
		}
		try {
			p.setP_image(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setName(name);
		p.setPrice(price);
		p.setAvailable(isAvailable);
		Category cat = categoryService.getCategoryById(category_id);
		p.setCategory(cat);
		productService.saveProduct(p);
		return ResponseEntity.ok(new MessageResponse("Product registered successfully!"));
	}

	/**
	 * Delete product by its id. http://localhost:8088/products/203
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}

}