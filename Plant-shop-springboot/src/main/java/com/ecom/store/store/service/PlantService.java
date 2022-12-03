package com.ecom.store.store.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ecom.store.store.domain.Plant;

public interface PlantService {

	List<Plant> findAllPlants();
	
	Page<Plant> findPlantsByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, List<String> Sizes,
			List<String> categories, String search);
		
	List<Plant> findFirstPlants();

	Plant findPlantById(Long id);
	
	Plant savePlant(Plant plant);

	void deletePlantById(Long id);
	
	List<String> getAllSizes();

	List<String> getAllCategories();


}
