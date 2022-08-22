package com.dipak.shop.service;

import java.util.List;

import com.dipak.shop.entity.ProductType;


public interface ProductTypeService {
	public ProductType findProductTypeById(Long Id);
	public ProductType findProductTypeByName(String name);
	public List<ProductType> getProductTypes();
	public ProductType saveProductType(ProductType productType);
	public boolean deleteProductType(Long producTypeId);
}
