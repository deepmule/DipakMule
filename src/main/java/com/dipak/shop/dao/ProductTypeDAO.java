package com.dipak.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dipak.shop.entity.ProductType;

public interface ProductTypeDAO extends JpaRepository<ProductType, Long> {

	ProductType findByName(String name);

}
