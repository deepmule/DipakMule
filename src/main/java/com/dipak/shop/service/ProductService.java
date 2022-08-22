package com.dipak.shop.service;

import java.util.List;

import com.dipak.shop.entity.Product;
import com.dipak.shop.model.ProductSerachModel;


public interface ProductService {
	public Product findProductById(Long Id);
	public Product findProductByName(String name);
	public List<Product> getProducts();
	public Product saveProduct(Product product);
	public boolean deleteProduct(Long productId);
	public List<Product> getProductsForProductSerachModel(ProductSerachModel productSerachModel);
}
