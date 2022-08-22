package com.dipak.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dipak.shop.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Long>{

	Product findByName(String name);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name,'%') and p.productType.name LIKE CONCAT('%',:type,'%') and  p.productType.category.name LIKE CONCAT('%',:category,'%') and p.price>=:minPrice")
	List<Product> search(@Param("name") String name,@Param("type") String type,@Param("category") String category,@Param("minPrice") Double minPrice);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name,'%') and p.productType.name LIKE CONCAT('%',:type,'%') and  p.productType.category.name LIKE CONCAT('%',:category,'%') and p.price>=:minPrice and p.price<=:maxPrice")
	List<Product> search(@Param("name") String name,@Param("type") String type,@Param("category") String category, @Param("minPrice") Double minPrice,@Param("maxPrice") Double maxPrice);

}
