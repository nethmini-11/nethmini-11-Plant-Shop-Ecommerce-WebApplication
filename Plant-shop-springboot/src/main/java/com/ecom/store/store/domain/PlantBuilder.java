package com.ecom.store.store.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlantBuilder {
		
	private String title;
	private int stock;	
	private double price;
	private String picture;
	private List<String> Sizes;
	private List<String> categories;
	
	public PlantBuilder() {
	}
	
	public PlantBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	
	public PlantBuilder stockAvailable(int stock) {
		this.stock = stock;
		return this;
	}
	
	public PlantBuilder withPrice(double price) {
		this.price = price;
		return this;
	}
	
	public PlantBuilder imageLink(String picture) {
		this.picture = picture;
		return this;
	}
	
	public PlantBuilder SizesAvailable(List<String> Sizes) {
		this.Sizes = Sizes;
		return this;
	}
	
	public PlantBuilder ofCategories(List<String> categories) {
		this.categories = categories;
		return this;
	}
	
	
	public Plant build() {
		Plant plant = new Plant();
		plant.setTitle(this.title);
		plant.setPrice(this.price);
		plant.setStock(this.stock);
		plant.setPicture(this.picture);		
		
		if (this.Sizes != null && !this.Sizes.isEmpty()) {
			Set<Size> SizeElements = new HashSet<>();
			for (String val : this.Sizes) {
				SizeElements.add(new Size(val,plant));
			}	
			plant.setSizes(SizeElements);
		}
		
		if (this.categories != null && !this.categories.isEmpty() ) {
			Set<Category> catElements = new HashSet<>();
			for (String val : this.categories) {
				catElements.add(new Category(val,plant));
			}
			plant.setCategories(catElements);
		}		
		
		return plant;
	}
	
}