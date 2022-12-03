package com.ecom.store.store.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.store.store.domain.Plant;
import com.ecom.store.store.domain.PlantBuilder;
import com.ecom.store.store.domain.Category;
import com.ecom.store.store.domain.Size;
import com.ecom.store.store.service.PlantService;

@Controller
@RequestMapping("/plant")
public class PlantController {

	@Autowired
	private PlantService plantService;
	
	@RequestMapping("/add")
	public String addPlant(Model model) {
		Plant plant = new Plant();
		model.addAttribute("plant", plant);
		model.addAttribute("allSizes", plantService.getAllSizes());
		model.addAttribute("allCategories", plantService.getAllCategories());
		return "addPlant";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addPlantPost(@ModelAttribute("plant") Plant plant, HttpServletRequest request) {
		Plant newPlant = new PlantBuilder()
				.withTitle(plant.getTitle())
				.stockAvailable(plant.getStock())
				.withPrice(plant.getPrice())
				.imageLink(plant.getPicture())
				.SizesAvailable(Arrays.asList(request.getParameter("Size").split("\\s*,\\s*")))
				.ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
				.build();		
		plantService.savePlant(newPlant);	
		return "redirect:plant-list";
	}
	
	@RequestMapping("/plant-list")
	public String plantList(Model model) {
		List<Plant> plants = plantService.findAllPlants();
		model.addAttribute("plants", plants);
		return "plantList";
	}
	
	@RequestMapping("/edit")
	public String editPlant(@RequestParam("id") Long id, Model model) {
		Plant plant = plantService.findPlantById(id);
		String preselectedSizes = "";
		for (Size Size : plant.getSizes()) {
			preselectedSizes += (Size.getValue() + ",");
		}
		String preselectedCategories = "";
		for (Category category : plant.getCategories()) {
			preselectedCategories += (category.getName() + ",");
		}		
		model.addAttribute("plant", plant);
		model.addAttribute("preselectedSizes", preselectedSizes);
		model.addAttribute("preselectedCategories", preselectedCategories);
		model.addAttribute("allSizes", plantService.getAllSizes());
		model.addAttribute("allCategories", plantService.getAllCategories());
		return "editPlant";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editPlantPost(@ModelAttribute("plant") Plant plant, HttpServletRequest request) {		
		Plant newPlant = new PlantBuilder()
				.withTitle(plant.getTitle())
				.stockAvailable(plant.getStock())
				.withPrice(plant.getPrice())
				.imageLink(plant.getPicture())
				.SizesAvailable(Arrays.asList(request.getParameter("Size").split("\\s*,\\s*")))
				.ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
				.build();
		newPlant.setId(plant.getId());
		plantService.savePlant(newPlant);	
		return "redirect:plant-list";
	}
	
	@RequestMapping("/delete")
	public String deletePlant(@RequestParam("id") Long id) {
		plantService.deletePlantById(id);
		return "redirect:plant-list";
	}
	
}
