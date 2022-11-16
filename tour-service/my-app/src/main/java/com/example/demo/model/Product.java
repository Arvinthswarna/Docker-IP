package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	
	@Column(name="ProductName")
	private String productName;
	
	@Column(name="productQty")
	private String productQty;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String productName, String productQty) {
		super();
		this.productName = productName;
		this.productQty = productQty;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductQty() {
		return productQty;
	}

	public void setProductQty(String productQty) {
		this.productQty = productQty;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productQty=" + productQty + "]";
	}
	
	

	
	
	
	
}
