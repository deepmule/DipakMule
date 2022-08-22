package com.dipak.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dipak.shop.entity.ProductCategory;

public interface ProductCategoryDAO extends JpaRepository<ProductCategory, Long> {

	ProductCategory findByName(String name);

}
