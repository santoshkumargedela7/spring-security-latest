package com.teleapps.security.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.teleapps.security.entity.UserInfo;
import com.teleapps.security.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teleapps.security.dto.Product;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {

	@Autowired
	private UserInfoRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	List<Product> productList = null;

	@PostConstruct
	public void loadProductFromDB() {
		productList = IntStream
				.rangeClosed(1, 100).mapToObj(i -> Product.builder().productId(i).name("product " + i)
						.qty(new Random().nextInt(10)).price(new Random().nextInt(5000)).build())
				.collect(Collectors.toList());
	}

	public List<Product> getProducts() {
		return productList;
	}
	
	public Product getProduct(int id) {
		return productList.stream().filter(product -> product.getProductId()==id)
				.findAny()
				.orElseThrow(()-> new RuntimeException("product "+id+" not found"));
	}

	public String addUser(UserInfo userInfo){
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		repository.save(userInfo);
		return "user added to the system";
	}

}
