package com.example.rabelo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabelo.domain.product.Product;
import com.example.rabelo.domain.product.ProductRepository;
import com.example.rabelo.domain.product.RequestProduct;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	
	@Autowired
	private ProductRepository repository;
	
	@GetMapping
	public ResponseEntity getAllProducts() {
		var allProducts = repository.findAllByActiveTrue();
		return ResponseEntity.ok(allProducts);
	}
	
	@PostMapping
	public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data) {
		Product newProduct = new Product(data);
		repository.save(newProduct);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data) {
		Optional<Product> optionalProduct = repository.findById(data.id());
		if (optionalProduct.isPresent()) {
		Product product = optionalProduct.get();
		product.setName(data.name());
		product.setPrice_in_cents(data.price_in_cents());
		return ResponseEntity.ok(product);
	}else {
		return ResponseEntity.notFound().build();
	}
	
	}
	
	@DeleteMapping("/{id}") // pegando o id como variavel 
	@Transactional
	public ResponseEntity deleteProduct(@PathVariable String id) {
		Optional<Product> item = repository.findById(id);
		
		if (item.isPresent()) {
			Product product = item.get();
		    product.setActive(false);
		return ResponseEntity.noContent().build();
		
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}








