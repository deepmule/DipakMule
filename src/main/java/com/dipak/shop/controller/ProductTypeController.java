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
import com.dipak.shop.entity.ProductType;
import com.dipak.shop.model.ProductTypeModel;
import com.dipak.shop.service.ProductCategoryService;
import com.dipak.shop.service.ProductTypeService;

@RestController
@RequestMapping("/type")
public class ProductTypeController {
	@Autowired
	ProductTypeService productTypeService;
	@Autowired
	ProductCategoryService productCategoryService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getAllProductTypes(){
		Map<String, Object> result=new HashMap<String, Object>();
		List<ProductType> productTypes=productTypeService.getProductTypes();
		if(productTypes!=null && !productTypes.isEmpty()) {
			result.put("productTypes", productTypes);
			result.put("count", productTypes.size());
		}else {
			result.put("error", "ProductTypes list is empty");
		}
		return result;
	}
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getProductTypeById(@PathVariable("id") Long id){
		Map<String, Object> result=new HashMap<String, Object>();
		ProductType productType=productTypeService.findProductTypeById(id);
		if(productType!=null) {
			result.put("productType", productType);
		}else {
			result.put("error", "ProductType not found for Id = "+id);
		}
		return result;
	}
	
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> deleteProductTypeById(@PathVariable("id") Long id){
		Map<String, Object> result=new HashMap<String, Object>();
		ProductType productType=productTypeService.findProductTypeById(id);
		if(productTypeService.deleteProductType(id)) {
			result.put("productType", productType);
			result.put("sucess", "productType deleted sucessfully");
		}else {
			result.put("error", "ProductType not found for Id = "+id);
		}
		return result;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> createNewProductType(@RequestBody ProductTypeModel productTypeModel){
		Map<String, Object> result=new HashMap<String, Object>();
		ProductType productTypeSaved=null;
		ProductCategory productCategory=null;
		
		if(productTypeModel.getCategoryId()!=null) {
			productCategory=productCategoryService.findProductCategoryById(productTypeModel.getCategoryId());
			if(productCategory == null) {
				result.put("error", "Invalid categoryId="+productTypeModel.getCategoryId());
				return result;
			}
		}
		if(productTypeModel.getName()!=null && productCategory!=null) {
			ProductType newProductType=new ProductType();
			newProductType.setCategory(productCategory);
			newProductType.setName(productTypeModel.getName());
			productTypeSaved=productTypeService.saveProductType(newProductType);
		}
		
		if(productTypeSaved != null && productTypeSaved.getTypeId()!=null) {
			result.put("productType", productTypeSaved);
			result.put("success", "ProductType Created successfuly");
		}else {
			result.put("error", "Filed to create new productType");
			result.put("productType", productTypeModel);
		}
		return result;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateProductTypee(@RequestBody ProductTypeModel productTypeModel){
		Map<String, Object> result=new HashMap<String, Object>();
		ProductType productTypeSaved=null;
		ProductCategory productCategory=null;
		if(productTypeModel.getCategoryId()!=null) {
			productCategory=productCategoryService.findProductCategoryById(productTypeModel.getCategoryId());
			if(productCategory == null) {
				result.put("error", "Invalid categoryId="+productTypeModel.getCategoryId());
				return result;
			}
		}
		if(productTypeModel.getTypeId()!=null && result.isEmpty()) {
			ProductType newProductType=new ProductType();
			newProductType.setCategory(productCategory);
			newProductType.setName(productTypeModel.getName());
			newProductType.setTypeId(productTypeModel.getTypeId());
			productTypeSaved=productTypeService.saveProductType(newProductType);
		}
		
		if(productTypeSaved != null && productTypeSaved.getTypeId()!=null) {
			result.put("productType", productTypeSaved);
			result.put("success", "ProductType Updated successfuly");
		}else {
			result.put("error", "Filed to update productType with id ="+productTypeModel.getTypeId());
		}
		return result;
	}
}
