package com.dipak.shop.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipak.shop.dao.ProductDAO;
import com.dipak.shop.dao.ProductTypeDAO;
import com.dipak.shop.entity.Product;
import com.dipak.shop.model.ProductModel;
import com.dipak.shop.model.ProductSerachModel;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public Product findProductById(Long Id) {
		return productDAO.findById(Id).orElse(null);
	}
	
	@Override
	public Product findProductByName(String name) {
		if(name==null ||  name.isEmpty()) {
			return productDAO.findByName(name);
		}
		return null;
	}
	
	@Override
	public List<Product> getProducts() {
		return productDAO.findAll();
	}
	
	
	@Override
	public Product saveProduct(Product product) {
		if(product.getProductId() != null) {
			Product existProduct=productDAO.getById(product.getProductId());
			if(existProduct!=null) {
				existProduct.update(product);
				return productDAO.save(existProduct);
			}
		}else {
			return productDAO.save(product);
		}
		return null;
	}

	@Override
	public boolean deleteProduct(Long productId) {
		Product existProduct=productDAO.findById(productId).orElse(null);
		if(existProduct!=null) {
			productDAO.delete(existProduct);
			return true;
		}
		return false;
	}

	@Override
	public List<Product> getProductsForProductSerachModel(ProductSerachModel productSerachModel) {
		List<Product> products=null;
		productSerachModel.valided();
 		if(productSerachModel.getMaxPrice()!=null && productSerachModel.getMaxPrice()>0.0) {
			Double minPrice=productSerachModel.getMaxPrice()!=null?productSerachModel.getMinPrice():0.0;
			products= productDAO.search(productSerachModel.getName(),productSerachModel.getType(),productSerachModel.getCategory(),minPrice,productSerachModel.getMaxPrice());
		}else {
			products= productDAO.search(productSerachModel.getName(),productSerachModel.getType(),productSerachModel.getCategory(),0.0);
		}
 		
 		if(products!=null && !products.isEmpty()) {
 			if(productSerachModel.isSortMaxPriceFirst()) {
 				return products.stream().sorted((p1,p2)-> Double.compare(p2.getPrice(), p1.getPrice())).collect(Collectors.toList());
 			}else if(productSerachModel.isSortMinPriceFirst()) {
 				return products.stream().sorted((p1,p2)-> Double.compare(p1.getPrice(), p2.getPrice())).collect(Collectors.toList());
 			}
 			return products;
 		}
		return null;
	}
}
