package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins="*")
public class ProductController {
	
	@Autowired
	ProductRepository prodRepo;
	
	@PostMapping("/")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		try {
			Product _product = prodRepo.save(new Product(product.getProductName(),product.getProductQty()));
			return new ResponseEntity<>(_product,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Product>> getAllProducts(){
		try {
			List<Product> prods = new ArrayList<Product>();
			prodRepo.findAll().forEach(prods::add);
			if(prods.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(prods, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}	
	}
//	
	@GetMapping("/fetch_product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id){
		Optional<Product> ProductData = prodRepo.findById(id);
		if(ProductData.isPresent()) {
			return new ResponseEntity<>(ProductData.get(), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}
	
	@PutMapping("/update_product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product){
		Optional<Product> ProductData = prodRepo.findById(id);
		if(ProductData.isPresent()) {
			Product _Product = ProductData.get();
			_Product.setProductName(product.getProductName());
			_Product.setProductQty(product.getProductQty());
			return new ResponseEntity<>(prodRepo.save(_Product),HttpStatus.OK);
			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete_product/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id){
		try {
			prodRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
