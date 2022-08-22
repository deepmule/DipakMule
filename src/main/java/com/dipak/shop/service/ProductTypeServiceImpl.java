package com.dipak.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipak.shop.dao.ProductTypeDAO;
import com.dipak.shop.entity.ProductType;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{
	@Autowired
	private ProductTypeDAO productTypeDAO;
	
	@Override
	public ProductType findProductTypeById(Long Id) {
		return productTypeDAO.findById(Id).orElse(null);
	}
	
	@Override
	public ProductType findProductTypeByName(String name) {
		if(name==null ||  name.isEmpty()) {
			return productTypeDAO.findByName(name);
		}
		return null;
	}
	
	@Override
	public List<ProductType> getProductTypes() {
		return productTypeDAO.findAll();
	}
	
	
	@Override
	public ProductType saveProductType(ProductType productType) {
		if(productType.getTypeId() != null) {
			ProductType existProductType=productTypeDAO.getById(productType.getTypeId());
			if(existProductType!=null) {
				existProductType.update(productType);
				return productTypeDAO.save(existProductType);
			}
		}else {
			return productTypeDAO.save(productType);
		}
		return null;
	}

	@Override
	public boolean deleteProductType(Long productTypeId) {
		ProductType existProductType=productTypeDAO.findById(productTypeId).orElse(null);
		if(existProductType!=null) {
			productTypeDAO.delete(existProductType);
			return true;
		}
		return false;
	}
}
