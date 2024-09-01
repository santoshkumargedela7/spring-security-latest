package com.teleapps.security.controller;

import java.util.List;

import com.teleapps.security.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.teleapps.security.dto.Product;
import com.teleapps.security.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;
	
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome this endpoint is not secure";
	}



	@PostMapping("/new")
	public String addNewUser( @RequestBody  UserInfo userInfo){
		return productService.addUser(userInfo);
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Product>getAllTheProducts(){
		return productService.getProducts();
		
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Product getProductById(@PathVariable int id ) {
		return productService.getProduct(id);
	}
}

