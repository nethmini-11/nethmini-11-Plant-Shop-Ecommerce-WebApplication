package com.ecom.store.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.store.store.domain.Plant;
import com.ecom.store.store.repository.PlantRepository;
import com.ecom.store.store.repository.PlantSpecification;
import com.ecom.store.store.service.PlantService;

@Service
@Transactional
public class PlantServiceImpl implements PlantService {

	@Autowired
	private PlantRepository plantRepository;
	
	@Value("${plantservice.featured-items-number}")
	private int featuredPlantsNumber;

	@Override
	public List<Plant> findAllPlants() {
		return (List<Plant>) plantRepository.findAllEagerBy();
	}
	
	@Override
	public Page<Plant> findPlantsByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, 
										List<String> sizes, List<String> categories, String search) {		
		Page<Plant> page = plantRepository.findAll(PlantSpecification.filterBy(priceLow, priceHigh, sizes, categories, search), pageable);
        return page;		
	}	
	
	@Override
	public List<Plant> findFirstPlants() {
		return plantRepository.findAll(PageRequest.of(0,featuredPlantsNumber)).getContent(); 
	}

	@Override
	public Plant findPlantById(Long id) {
		Optional<Plant> opt = plantRepository.findById(id);
		return opt.get();
	}

	@Override
	@CacheEvict(value = { "sizes", "categories" }, allEntries = true)
	public Plant savePlant(Plant plant) {
		return plantRepository.save(plant);
	}
	
	@Override
	@CacheEvict(value = { "sizes", "categories" }, allEntries = true)
	public void deletePlantById(Long id) {
		plantRepository.deleteById(id);		
	}

	
	@Override
	@Cacheable("sizes")
	public List<String> getAllSizes() {
		return plantRepository.findAllSizes();
	}

	@Override
	@Cacheable("categories")
	public List<String> getAllCategories() {
		return plantRepository.findAllCategories();
	}

}
