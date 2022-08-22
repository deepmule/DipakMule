package com.dipak.shop.model;

import java.io.Serializable;

public class ProductSerachModel implements Serializable {
	private String name;
	private String type;
	private String category;
	private Double minPrice;
	private Double maxPrice;
	private boolean sortMinPriceFirst=false;
	private boolean sortMaxPriceFirst=false;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public boolean isSortMinPriceFirst() {
		return sortMinPriceFirst;
	}
	public void setSortMinPriceFirst(boolean sortMinPriceFirst) {
		this.sortMinPriceFirst = sortMinPriceFirst;
	}
	public boolean isSortMaxPriceFirst() {
		return sortMaxPriceFirst;
	}
	public void setSortMaxPriceFirst(boolean sortMaxPriceFirst) {
		this.sortMaxPriceFirst = sortMaxPriceFirst;
	}
	public void valided() {
		this.name=this.name==null?"":this.name;
		this.category=this.category==null?"":this.category;
		this.type=this.type==null?"":this.type;
	}
}
