package com.dipak.shop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_type")
public class ProductType implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long typeId;
	private String name;
	@ManyToOne
	@JoinColumn(name = "categoryId", nullable = false)
	private ProductCategory category;
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "ProductType [typeId=" + typeId + ", name=" + name + ", category=" + category + "]";
	}
	public void update(ProductType productType) {
		this.name=productType.name!=null && !productType.name.isEmpty() ? productType.name:this.name;
		this.category=productType.category!=null? productType.category:this.category;
	}
	
	
}
