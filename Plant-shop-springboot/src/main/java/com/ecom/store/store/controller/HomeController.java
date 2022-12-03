package com.ecom.store.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.store.store.domain.Plant;
import com.ecom.store.store.service.PlantService;

@Controller
public class HomeController {
		
	@Autowired
	private PlantService plantService;
	
	
	@RequestMapping("/")
	public String index(Model model) {		
		List<Plant> plants = plantService.findFirstPlants();
		model.addAttribute("plants", plants);
		return "index";
	}

	
}
