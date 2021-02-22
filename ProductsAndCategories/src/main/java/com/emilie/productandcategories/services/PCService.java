package com.emilie.productandcategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emilie.productandcategories.models.Category;
import com.emilie.productandcategories.models.Product;
import com.emilie.productandcategories.repositories.CategoryRepository;
import com.emilie.productandcategories.repositories.ProductRepository;

@Service
public class PCService {
	private final ProductRepository pRepo;
	private final CategoryRepository cRepo;
	
	public PCService(ProductRepository pRepo, CategoryRepository cRepo) {
		this.pRepo = pRepo;
		this.cRepo = cRepo;
	}
	
	//Find All Products
	public List<Product> findAllProducts() {
		return pRepo.findAll();
	}
	
	//Find All Categories
	public List<Category> findAllCategory() {
		return cRepo.findAll();
	}
	
	//Find Product by ID
	public Product findProductById(Long id) {
		Optional<Product> product = pRepo.findById(id);
		if (product.isPresent()) {
			return product.get();
		} else {
			return null;
		}
	}
	//Find Category by ID
	public Category findCategoryById(Long id) {
		Optional<Category> category = cRepo.findById(id);
		if (category.isPresent()) {
			return category.get();
		} else {
			return null;
		}
	}
	
	//Add new Product
	public Product createProduct(Product p) {
		return pRepo.save(p);
	}
	
	//Add new Category
	public Category createCategory(Category c) {
		return cRepo.save(c);
	}
	
	//Add Category to Product
	public Product addCategoryToProduct(Long product_id, Long category_id) {
		Optional<Category> category = cRepo.findById(category_id);
		Optional<Product> product = pRepo.findById(product_id);
		List<Category> clist = product.get().getCategories();
		clist.add(category.get());
		product.get().setCategories(clist);
		return pRepo.save(product.get());
	}
	
	//Add Product to Category
	public Category addProductToCategory(Long product_id, Long category_id) {
		Optional<Category> category = cRepo.findById(category_id);
		Optional<Product> product = pRepo.findById(product_id);
		List<Product> plist = category.get().getProducts();
		plist.add(product.get());
		category.get().setProducts(plist);
		return cRepo.save(category.get());
	}
	// filter the categories not assigned to a product
	public List<Category> otherCategory(Long product_id) {
		Optional<Product> product = pRepo.findById(product_id);
		List<Category> allcategories = cRepo.findAll();
		List<Category> thiscategories = product.get().getCategories();
		List<Category> retcategory = cRepo.findAll();
		if(thiscategories == null) {
			return allcategories;
		} else {
			
			for(Category c: allcategories) {
				if (thiscategories.contains(c)) {
					retcategory.remove(c);
				}
			}
			return retcategory;
		}
	}
	// filter the products not assigned to a category
	public List<Product> otherProduct(Long category_id) {
		Optional<Category> category = cRepo.findById(category_id);
		List<Product> allproducts = pRepo.findAll();
		List<Product> retproduct = pRepo.findAll();
		List<Product> thisproducts = category.get().getProducts();
		for (Product p: allproducts) {
			if(thisproducts.contains(p)) {
				retproduct.remove(p);
			}
		}
		return retproduct;

	}
}
