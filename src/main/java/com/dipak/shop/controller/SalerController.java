package com.dipak.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dipak.shop.entity.Saler;
import com.dipak.shop.service.SalerService;

@RestController
@RequestMapping("/saler")
public class SalerController {
	@Autowired
	SalerService salerService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getAllSalers(){
		Map<String, Object> result=new HashMap<String, Object>();
		List<Saler> salers=salerService.getSalers();
		if(salers!=null && !salers.isEmpty()) {
			result.put("salers", salers);
			result.put("count", salers.size());
		}else {
			result.put("error", "Salers list is empty");
		}
		return result;
	}
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getSalerById(@PathVariable("id") Long id){
		Map<String, Object> result=new HashMap<String, Object>();
		Saler saler=salerService.findSalerById(id);
		if(saler!=null) {
			result.put("saler", saler);
		}else {
			result.put("error", "Saler not found for Id = "+id);
		}
		return result;
	}
	
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> deleteSalerById(@PathVariable("id") Long id){
		Map<String, Object> result=new HashMap<String, Object>();
		Saler saler=salerService.findSalerById(id);
		if(salerService.deleteSaler(id)) {
			result.put("saler", saler);
			result.put("sucess", "saler deleted sucessfully");
		}else {
			result.put("error", "Saler not found for Id = "+id);
		}
		return result;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> createNewSaler(@RequestBody Saler newSaler){
		Map<String, Object> result=new HashMap<String, Object>();
		newSaler.setSalerId(null);
		Saler salerSaved=null;
		if(newSaler.getName()!=null) {
			salerSaved=salerService.saveSaler(newSaler);
		}
		
		if(salerSaved != null && salerSaved.getSalerId()!=null) {
			result.put("saler", salerSaved);
			result.put("success", "Saler Created successfuly");
		}else {
			result.put("error", "Filed to create new saler");
			Saler errorSaler=new Saler("","");
			errorSaler.update(newSaler);
			result.put("saler", errorSaler);
		}
		return result;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateSalere(@RequestBody Saler newSaler){
		Map<String, Object> result=new HashMap<String, Object>();
		Saler salerSaved=null;
		if(newSaler.getSalerId()!=null) {
			salerSaved=salerService.saveSaler(newSaler);
		}
		
		if(salerSaved != null && salerSaved.getSalerId()!=null) {
			result.put("saler", salerSaved);
			result.put("success", "Saler Updated successfuly");
		}else {
			result.put("error", "Filed to update saler with id ="+newSaler.getSalerId());
		}
		return result;
	}
}
