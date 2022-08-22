package com.dipak.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipak.shop.dao.ProductCategoryDAO;
import com.dipak.shop.entity.ProductCategory;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
	@Autowired
	private ProductCategoryDAO productCategoryDAO;
	
	@Override
	public ProductCategory findProductCategoryById(Long Id) {
		return productCategoryDAO.findById(Id).orElse(null);
	}
	
	@Override
	public ProductCategory findProductCategoryByName(String name) {
		if(name==null ||  name.isEmpty()) {
			return productCategoryDAO.findByName(name);
		}
		return null;
	}
	
	@Override
	public List<ProductCategory> getProductCategorys() {
		return productCategoryDAO.findAll();
	}
	
	
	@Override
	public ProductCategory saveProductCategory(ProductCategory ProductCategory) {
		if(ProductCategory.getCategoryId() != null) {
			ProductCategory existProductCategory=productCategoryDAO.getById(ProductCategory.getCategoryId());
			if(existProductCategory!=null) {
				existProductCategory.update(ProductCategory);
				return productCategoryDAO.save(existProductCategory);
			}
		}else {
			return productCategoryDAO.save(ProductCategory);
		}
		return null;
	}

	@Override
	public boolean deleteProductCategory(Long productCategoryId) {
		ProductCategory existProductCategory=productCategoryDAO.findById(productCategoryId).orElse(null);
		if(existProductCategory!=null) {
			productCategoryDAO.delete(existProductCategory);
			return true;
		}
		return false;
	}
}
