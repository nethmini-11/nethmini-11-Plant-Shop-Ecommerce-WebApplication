package com.ecom.store.store.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.store.store.domain.Plant;
import com.ecom.store.store.form.PlantFilterForm;
import com.ecom.store.store.service.PlantService;
import com.ecom.store.store.type.SortFilter;

@Controller
public class StoreController {
	
	@Autowired
	private PlantService plantService;
	
	@RequestMapping("/store")
	public String store(@ModelAttribute("filters") PlantFilterForm filters, Model model) {
		Integer page = filters.getPage();			
		int pagenumber = (page == null ||  page <= 0) ? 0 : page-1;
		SortFilter sortFilter = new SortFilter(filters.getSort());	
		Page<Plant> pageresult = plantService.findPlantsByCriteria(PageRequest.of(pagenumber,9, sortFilter.getSortType()), 
																filters.getPricelow(), filters.getPricehigh(), 
																filters.getSize(), filters.getCategory(), filters.getSearch());	
		model.addAttribute("allCategories", plantService.getAllCategories());
		model.addAttribute("allSizes", plantService.getAllSizes());
		model.addAttribute("plants", pageresult.getContent());
		model.addAttribute("totalitems", pageresult.getTotalElements());
		model.addAttribute("itemsperpage", 9);
		return "store";
	}
	
	
	@RequestMapping("/plant-detail")
	public String plantDetail(@PathParam("id") Long id, Model model) {
		Plant plant = plantService.findPlantById(id);
		model.addAttribute("plant", plant);
		model.addAttribute("notEnoughStock", model.asMap().get("notEnoughStock"));
		model.addAttribute("addPlantSuccess", model.asMap().get("addPlantSuccess"));
		return "plantDetail";
	}
	

}
