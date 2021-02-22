package com.emilie.productandcategories.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.emilie.productandcategories.models.Category;
import com.emilie.productandcategories.models.Product;
import com.emilie.productandcategories.services.PCService;

@Controller
public class PCController {
	@Autowired
	private final PCService pcservice;
	
	public PCController(PCService pcservice) {
		this.pcservice = pcservice;
	}
	//root direct
	@RequestMapping("/")
	public String root() {
		return "redirect:/products/new";
	}
	
	//new product page
	@RequestMapping("/products/new")
	public String newProduct(@ModelAttribute("product") Product product) {
		return "newproduct.jsp";
	}
	
	//create product
	@RequestMapping(value="/products/create", method=RequestMethod.POST)
	public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		if(result.hasErrors()) {
			return "newproduct.jsp";
		} else {
			pcservice.createProduct(product);
			return "redirect:/products/new";
		}
	}
	
	//new category page
	@RequestMapping("/categories/new")
	public String newCategory(@ModelAttribute("category") Category category) {
		return "newcategory.jsp";
	}
	//create category
	@RequestMapping(value="/categories/create", method=RequestMethod.POST)
	public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
		if (result.hasErrors()) {
			return "newcategory.jsp";
		} else {
			pcservice.createCategory(category);
			return "redirect:/categories/new";
		}
	}
	//product detail page --- list of all categories
	@RequestMapping("/products/{product_id}")
	public String productDetail(@PathVariable("product_id") Long product_id, Model model) {
		List<Category> othercategories = pcservice.otherCategory(product_id);
		Product product = pcservice.findProductById(product_id);
		model.addAttribute("product", product);
		model.addAttribute("categories", othercategories);
		
		List<Category> allcategories = pcservice.findAllCategory();
		model.addAttribute("allcategories", allcategories);

		return "productdetail.jsp";	
	}
	//add a category to the product
	@RequestMapping(value="/products/{product_id}/add", method=RequestMethod.POST)
	public String addCategory(@PathVariable("product_id") Long product_id, @RequestParam("category_id") String thecategory_id) {
		Long category_id = Long.parseLong(thecategory_id);
		pcservice.addCategoryToProduct(product_id, category_id);
		return "redirect:/products/" + product_id;
	}
	//category detail page --- list of all products
	@RequestMapping("/categories/{category_id}")
	public String categoryDetail(@PathVariable("category_id") Long category_id, Model model) {
		List<Product> allproducts = pcservice.otherProduct(category_id);
		Category category = pcservice.findCategoryById(category_id);
		model.addAttribute("category", category);
		model.addAttribute("products", allproducts);
		return "categorydetail.jsp";
	}
	
	//add a product to the category
	@RequestMapping(value="/categories/{category_id}/add", method=RequestMethod.POST)
	public String addProduct(@PathVariable("category_id") Long category_id, @RequestParam("product_id") String theproduct_id) {
		Long product_id = Long.parseLong(theproduct_id);
		pcservice.addProductToCategory(product_id, category_id);
		return "redirect:/categories/" + category_id;
	}

}
