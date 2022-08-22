package com.dipak.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;

	@ManyToOne
	@JoinColumn(name = "typeId", nullable = false)
	private ProductType productType;
	@ManyToOne
	@JoinColumn(name = "salerId", nullable = false)
	private Saler saler;
	
	private String name;
	private String description;
	private Integer quantity;
	private double price;
	private String image;
	private Date entryDate;
	private Date lastUpdated;
	private boolean active=true;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public Saler getSaler() {
		return saler;
	}
	public void setSaler(Saler saler) {
		this.saler = saler;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productType=" + productType + ", saler=" + saler + ", name="
				+ name + ", description=" + description + ", quantity=" + quantity + ", price=" + price + ", image="
				+ image + ", entryDate=" + entryDate + ", lastUpdated=" + lastUpdated + ", active=" + active + "]";
	}
	public void update(Product product) {
		this.active=product.active;
		this.name=product.name!=null && !product.name.isEmpty() ? product.name:this.name;
		this.description=product.description!=null && !product.description.isEmpty() ? product.description:this.description;
		this.image=product.image!=null && !product.image.isEmpty() ? product.image:this.image;
		this.productType=product.productType!=null ? product.productType:this.productType;
		this.quantity=product.quantity!= null ? product.quantity:this.quantity;
		this.price=product.price>0.0 ? product.price:this.price;
		this.lastUpdated=new Date();
	}
	
}
