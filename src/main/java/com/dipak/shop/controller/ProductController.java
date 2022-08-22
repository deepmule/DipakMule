package com.dipak.shop.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.dipak.shop.entity.Product;
import com.dipak.shop.entity.ProductType;
import com.dipak.shop.entity.Saler;
import com.dipak.shop.model.ProductModel;
import com.dipak.shop.model.ProductSerachModel;
import com.dipak.shop.service.ProductService;
import com.dipak.shop.service.ProductTypeService;
import com.dipak.shop.service.SalerService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private SalerService salerService;
	@Autowired
	private ProductTypeService productTypeService;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getAllProducts(){
		Map<String, Object> result=new HashMap<String, Object>();
		List<Product> products=productService.getProducts();
		if(products!=null && !products.isEmpty()) {
			result.put("products", products.stream().map(p->toProductModel(p)).collect(Collectors.toList()));
			result.put("count", products.size());
		}else {
			result.put("error", "Products list is empty");
		}
		return result;
	}
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getProductById(@PathVariable("id") Long id){
		Map<String, Object> result=new HashMap<String, Object>();
		Product product=productService.findProductById(id);
		if(product!=null) {
			result.put("product", toProductModel(product));
		}else {
			result.put("error", "Product not found for Id = "+id);
		}
		return result;
	}
	
	@GetMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> search(@RequestBody ProductSerachModel productSerachModel){
		Map<String, Object> result=new HashMap<String, Object>();
		List<Product> products=productService.getProductsForProductSerachModel(productSerachModel);
		if(products!=null && !products.isEmpty()) {
			result.put("products", products.stream().map(p->toProductModel(p)).collect(Collectors.toList()));
			result.put("count", products.size());
		}else {
			result.put("error", "Products list is empty for serch options");
		}
		return result;
	}
	
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> deleteProductById(@PathVariable("id") Long id){
		Map<String, Object> result=new HashMap<String, Object>();
		Product product=productService.findProductById(id);
		if(productService.deleteProduct(id)) {
			result.put("product", toProductModel(product));
			result.put("sucess", "product deleted sucessfully");
		}else {
			result.put("error", "Product not found for Id = "+id);
		}
		return result;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> createNewProduct(@RequestBody ProductModel productModel){
		Map<String, Object> result=new HashMap<String, Object>();
		Product newProduct=getProductFromModel(productModel, result);
		if(!result.isEmpty()) {
			result.put("product", productModel);
			return result;
		}
		
		validateProduct(newProduct, result);;
		if(!result.isEmpty()) {
			result.put("product", productModel);
			return result;
		}
		
		newProduct.setProductId(null);
		Product productSaved=null;
		if(newProduct.getName()!=null) {
			productSaved=productService.saveProduct(newProduct);
		}
		
		if(productSaved != null && productSaved.getProductId()!=null) {
			result.put("product", toProductModel(productSaved));
			result.put("success", "Product Created successfuly");
		}else {
			result.put("error", "Filed to create new product");
			result.put("product", productModel);
		}
		return result;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateProducte(@RequestBody ProductModel productModel){
		Map<String, Object> result=new HashMap<String, Object>();
		Product productSaved=null;
		Product newProduct=getProductFromModel(productModel, result);
		productSaved=productService.saveProduct(newProduct);
		result.clear();
		if(productSaved != null && productSaved.getProductId()!=null) {
			result.put("product", toProductModel(productSaved));
			result.put("success", "Product Updated successfuly");
		}else {
			result.put("error", "Filed to update product with id ="+productModel.getProductId());
		}
		return result;
	}
	
	private void validateProduct(Product product, Map<String, Object> result) {
		Map<String, String> validations=new HashMap<String, String>();
		if(product.getName()==null || product.getName().isEmpty()) {
			validations.put("name", "It must not be empty");
		}
		if(product.getDescription()==null || product.getDescription().isEmpty()) {
			validations.put("description", "It must not be empty");
		}
		if(product.getQuantity()!=null && product.getQuantity()<0 ) {
			validations.put("quantity", "It must not less than 0");
		}
		if(product.getPrice()<0 ) {
			validations.put("price", "It must not less than 0");
		}
		if(!validations.isEmpty()) {
			result.put("validations", validations);
		}
		
	}
	
	private Product getProductFromModel(ProductModel productModel, Map<String, Object> result) {
		Map<String, String> errors=new HashMap<String, String>();
		Saler saler=productModel.getSalerId()!=null?salerService.findSalerById(productModel.getSalerId()):null;
		if(saler==null) {
			errors.put("salreId", "Invalid "+productModel.getSalerId());
		}
		ProductType productType= productModel.getTypeId()!=null?productTypeService.findProductTypeById(productModel.getTypeId()):null;
		if(productType==null) {
			errors.put("typeId", "Invalide "+productModel.getTypeId());
		}
		if(!errors.isEmpty()) {
			result.put("errors", errors);
			return null;
		}
		Product product=new Product();
		product.setProductId(productModel.getProductId());
		product.setProductType(productType);
		product.setSaler(saler);
		product.setName(productModel.getName());
		product.setDescription(productModel.getDescription());
		product.setImage(productModel.getImage());
		product.setPrice(productModel.getPrice());
		product.setQuantity(productModel.getQuantity());
		product.setEntryDate(new Date());
		product.setLastUpdated(new Date());
		return product;
		
	}
	
	private ProductModel toProductModel(Product product) {
		
		ProductModel productModel=new ProductModel();
		productModel.setProductId(product.getProductId());
		productModel.setTypeId(product.getProductType().getTypeId());
		productModel.setSalerId(product.getSaler().getSalerId());
		productModel.setSalerName(product.getSaler().getName());
		productModel.setCatagoryName(product.getProductType().getCategory().getName());
		productModel.setTypeName(product.getProductType().getName());
		productModel.setName(product.getName());
		productModel.setDescription(product.getDescription());
		productModel.setImage(product.getImage());
		productModel.setPrice(product.getPrice());
		productModel.setQuantity(product.getQuantity());
		return productModel;
		
	}
}
