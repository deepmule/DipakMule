package com.dipak.shop.service;

import java.util.List;

import com.dipak.shop.entity.ProductCategory;


public interface ProductCategoryService {
	public ProductCategory findProductCategoryById(Long Id);
	public ProductCategory findProductCategoryByName(String name);
	public List<ProductCategory> getProductCategorys();
	public ProductCategory saveProductCategory(ProductCategory productCategory);
	public boolean deleteProductCategory(Long productCategoryId);
}
