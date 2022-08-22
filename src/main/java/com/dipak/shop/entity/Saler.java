package com.dipak.shop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "saler")
public class Saler implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long salerId;
	private String name;
	private String address;
	public Saler() {}
	public Saler(String name,String address) {
		this.name=name;
		this.address=address;
	}
	
	public Long getSalerId() {
		return salerId;
	}
	public void setSalerId(Long salerId) {
		this.salerId = salerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Saler [salerId=" + salerId + ", name=" + name + ", address=" + address + "]";
	}
	
	public void update(Saler saler) {
		this.name=saler.name!=null && !saler.name.isEmpty()?saler.name:this.name;
		this.address=saler.address!=null && !saler.address.isEmpty()?saler.address:this.address;		
	}
	
}
