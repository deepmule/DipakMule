package com.dipak.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipak.shop.dao.SalerDAO;
import com.dipak.shop.entity.Saler;

@Service
public class SalerSeviceImpl implements SalerService {
	@Autowired
	private SalerDAO salerDAO;
	
	@Override
	public Saler findSalerById(Long Id) {
		return salerDAO.findById(Id).orElse(null);
	}
	
	@Override
	public Saler findSalerByName(String name) {
		if(name==null ||  name.isEmpty()) {
			return salerDAO.findByName(name);
		}
		return null;
	}
	
	@Override
	public List<Saler> getSalers() {
		return salerDAO.findAll();
	}
	
	
	@Override
	public Saler saveSaler(Saler saler) {
		if(saler.getSalerId() != null) {
			Saler existSaler=salerDAO.getById(saler.getSalerId());
			if(existSaler!=null) {
				existSaler.update(saler);
				return salerDAO.save(existSaler);
			}
		}else {
			return salerDAO.save(saler);
		}
		return null;
	}

	@Override
	public boolean deleteSaler(Long salerId) {
		Saler existSaler=salerDAO.findById(salerId).orElse(null);
		if(existSaler!=null) {
			salerDAO.delete(existSaler);
			return true;
		}
		return false;
	}

}
