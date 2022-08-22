package com.dipak.shop.service;

import java.util.List;

import com.dipak.shop.entity.Saler;

public interface SalerService {
	public Saler findSalerById(Long Id);
	public Saler findSalerByName(String name);
	public List<Saler> getSalers();
	public Saler saveSaler(Saler saler);
	public boolean deleteSaler(Long salerId);
	
}
