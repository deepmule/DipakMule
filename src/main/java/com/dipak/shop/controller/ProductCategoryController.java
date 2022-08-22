package com.dipak.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dipak.shop.entity.ProductCategory;
import com.dipak.shop.service.ProductCategoryService;
@RestController
@RequestMapping("/category")
public class ProductCategoryController {
	@Autowired
	ProductCategoryService productCategoryService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getAllProductCategorys(){
		Map<String, Object> result=new HashMap<String, Object>();
		List<ProductCategory> productCategorys=productCategoryService.getProductCategorys();
		if(productCategorys!=null && !productCategorys.isEmpty()) {
			result.put("productCategorys", productCategorys);
			result.put("count", productCategorys.size());
		}else {
			result.put("error", "ProductCategorys list is empty");
		}
		return result;
	}
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getProductCategoryById(@PathVariable("id") Long id){
		Map<String, Object> result=new HashMap<String, Object>();
		ProductCategory productCategory=productCategoryService.findProductCategoryById(id);
		if(productCategory!=null) {
			result.put("productCategory", productCategory);
		}else {
			result.put("error", "ProductCategory not found for Id = "+id);
		}
		return result;
	}
	
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> deleteProductCategoryById(@PathVariable("id") Long id){
		Map<String, Object> result=new HashMap<String, Object>();
		ProductCategory productCategory=productCategoryService.findProductCategoryById(id);
		if(productCategoryService.deleteProductCategory(id)) {
			result.put("productCategory", productCategory);
			result.put("sucess", "productCategory deleted sucessfully");
		}else {
			result.put("error", "ProductCategory not found for Id = "+id);
		}
		return result;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> createNewProductCategory(@RequestBody ProductCategory newProductCategory){
		Map<String, Object> result=new HashMap<String, Object>();
		newProductCategory.setCategoryId(null);
		ProductCategory productCategorySaved=null;
		if(newProductCategory.getName()!=null) {
			productCategorySaved=productCategoryService.saveProductCategory(newProductCategory);
		}
		
		if(productCategorySaved != null && productCategorySaved.getCategoryId()!=null) {
			result.put("productCategory", productCategorySaved);
			result.put("success", "ProductCategory Created successfuly");
		}else {
			result.put("error", "Filed to create new productCategory");
			result.put("productCategory", newProductCategory);
		}
		return result;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateProductCategorye(@RequestBody ProductCategory newProductCategory){
		Map<String, Object> result=new HashMap<String, Object>();
		ProductCategory productCategorySaved=null;
		if(newProductCategory.getCategoryId()!=null) {
			productCategorySaved=productCategoryService.saveProductCategory(newProductCategory);
		}
		
		if(productCategorySaved != null && productCategorySaved.getCategoryId()!=null) {
			result.put("productCategory", productCategorySaved);
			result.put("success", "ProductCategory Updated successfuly");
		}else {
			result.put("error", "Filed to update productCategory with id ="+newProductCategory.getCategoryId());
		}
		return result;
	}
}
