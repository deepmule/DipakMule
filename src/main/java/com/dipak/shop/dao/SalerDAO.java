package com.dipak.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dipak.shop.entity.Saler;

public interface SalerDAO  extends JpaRepository<Saler, Long>{

	Saler findByName(String name);

}
