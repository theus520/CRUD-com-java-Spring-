package com.example.rabelo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabelo.domain.product.Product;
import com.example.rabelo.domain.product.ProductRepository;
import com.example.rabelo.domain.product.RequestProduct;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	
	@Autowired
	private ProductRepository repository;
	
	@GetMapping
	public ResponseEntity getAllProducts() {
		var allProducts = repository.findAll();
		return ResponseEntity.ok(allProducts);
	}
	
	@PostMapping
	public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data) {
		Product newProduct = new Product(data);
		System.out.println(data);
		repository.save(newProduct);
		return ResponseEntity.ok().build();
	}
}








